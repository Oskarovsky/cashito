package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.exception.DealNotFoundException;
import com.slyko.cashitoapplication.exception.UnexpectedDealVersionException;
import com.slyko.cashitoapplication.port.out.DealsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;
import com.slyko.cashitoinfra.adapter.spi.mapper.DealMapper;
import com.slyko.cashitoinfra.adapter.spi.mapper.ProductMapper;
import com.slyko.cashitoinfra.adapter.spi.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.spi.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DealDbAdapter implements DealsSecondaryPort {

    private static final Sort DEFAULT_SORT = Sort.by(Sort.Order.by("lastModifiedDate"));

    private final DealReactiveRepository dealReactiveRepository;
    private final ProductReactiveRepository productReactiveRepository;


    // TODO fix
    @Override
    public Mono<Deal> findById(UUID dealId, Long version, boolean loadRelations) {
        final Mono<Deal> dealMono = dealReactiveRepository.findById(dealId)
                .switchIfEmpty(Mono.error(new DealNotFoundException(dealId)))
                .handle((deal, sink) -> {
                    // Optimistic locking: pre-check
                    if (version != null && !version.equals(deal.getVersion())) {
                        // The version are different, return an error
                        sink.error(new UnexpectedDealVersionException(version, deal.getVersion()));
                    } else {
                        Deal api = DealMapper.toApi(deal);
                        sink.next(api);
                    }
                });
        // Load the related objects, if requested
        return loadRelations
                ? dealMono.flatMap(this::loadRelations)
                : dealMono;
    }

    /**
     * Find all deals
     * @return Find all deals with the related objects loaded
     */
    @Override
    public Flux<Deal> findAll() {
        return dealReactiveRepository
                .findAll()
                .map(DealMapper::toApi);
    }

    /**
     * Create a new deal
     * @param deal Deal to be created
     *
     * @return the saved deal without the related entities
     */
    @Override
    @Transactional
    public Mono<Deal> create(Deal deal) {
        return dealReactiveRepository
                .save(DealMapper.toDb(deal))
                .flatMap(savedDeal -> Flux.fromIterable(deal.getProducts())
                        .flatMap(product -> {
                            ProductEntity productEntity = ProductMapper.toDb(product);
                            productEntity.setDealId(savedDeal.getId());
                            return productReactiveRepository
                                    .save(productEntity)
                                    .map(ProductMapper::toApi);
                        })
                        .collectList()
                        .map(savedProducts -> {
                            savedDeal.setProducts(
                                    savedProducts.stream()
                                            .map(ProductMapper::toDb)
                                            .toList()
                            );
                            return savedDeal;
                        })
                        .then(Mono.just(savedDeal))
                )
                .map(DealMapper::toApi);
    }

    /**
     * Update an Item
     * @param dealRequest to be saved
     * @return the saved deal without the related entities
     */
    @Override
    public Mono<Deal> update(UUID id, Long version, Deal dealRequest) {
        if (dealRequest.getId() == null || dealRequest.getVersion() == null) {
            return Mono.error(new IllegalArgumentException("When updating an deal, the id and the version must be provided"));
        }
        return findById(id, version, false)
                .switchIfEmpty(Mono.error(new DealNotFoundException(dealRequest.getId())))
                .map(DealMapper::toDb)
                .flatMap(db -> {
                    Optional.ofNullable(dealRequest.getTitle()).ifPresent(db::setTitle);
                    Optional.ofNullable(dealRequest.getStatus()).ifPresent(db::setStatus);
                    Optional.ofNullable(dealRequest.getAccountId()).ifPresent(db::setAccountId);
                    return dealReactiveRepository.save(db);
                })
                .map(DealMapper::toApi);



    }

    /**
     * Load the objects related to an item
     * @param deal Deal
     * @return The deals with the loaded related objects (account, products)
     */
    private Mono<Deal> loadRelations(final Deal deal) {
        // Load the products
        return Mono.just(deal)
                .map(DealMapper::toDb)
                .zipWith(
                        productReactiveRepository
                                .findByDealId(deal.getId())
                                .collectList()
                )
                .map(t -> t.getT1().setProducts(t.getT2()))
                .map(DealMapper::toApi);
    }
}
