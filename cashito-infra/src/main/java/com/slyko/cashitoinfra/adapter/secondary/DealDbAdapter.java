package com.slyko.cashitoinfra.adapter.secondary;

import com.slyko.cashitoapplication.exception.DealNotFoundException;
import com.slyko.cashitoapplication.exception.UnexpectedDealVersionException;
import com.slyko.cashitodomain.port.out.DealsSecondaryPort;
import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitoinfra.adapter.secondary.entity.ProductEntity;
import com.slyko.cashitoinfra.adapter.secondary.mapper.DealMapper;
import com.slyko.cashitoinfra.adapter.secondary.mapper.ProductMapper;
import com.slyko.cashitoinfra.adapter.secondary.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DealDbAdapter implements DealsSecondaryPort {

    private static final Sort DEFAULT_SORT = Sort.by(Sort.Order.by("lastModifiedDate"));

    private final DealReactiveRepository dealReactiveRepository;
    private final ProductReactiveRepository productReactiveRepository;


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
     * @param dealApi to be saved
     * @return the saved deal without the related entities
     */
    @Override
    public Mono<Deal> update(UUID id, Long version, Deal dealApi) {
        if (dealApi.getId() == null || dealApi.getVersion() == null) {
            return Mono.error(new IllegalArgumentException("When updating an deal, the id and the version must be provided"));
        }
        return findById(id, version, false)
                .switchIfEmpty(Mono.error(new DealNotFoundException(dealApi.getId())))
                .map(DealMapper::toDb)
                .flatMap(db -> {
                    Optional.ofNullable(dealApi.getTitle()).ifPresent(db::setTitle);
                    Optional.ofNullable(dealApi.getStatus()).ifPresent(db::setStatus);
                    Optional.ofNullable(dealApi.getAccountId()).ifPresent(db::setAccountId);
                    return dealReactiveRepository.save(db);
                })
                .map(DealMapper::toApi);



    }

    @Override
    @Transactional
    public Mono<Void> delete(UUID id, Long version) {
        return findById(id, version, false)
                .zipWith(productReactiveRepository.deleteAllByDealId(id))
                .map(Tuple2::getT1)
                .flatMap(t -> dealReactiveRepository.delete(DealMapper.toDb(t)));
    }

    @Override
    public Mono<Deal> updateDealProducts(UUID id, Long version, Deal dealApi) {
        if (dealApi.getId() == null || dealApi.getVersion() == null) {
            return Mono.error(new IllegalArgumentException("When updating an deal, the id and the version must be provided"));
        }
        return findById(id, version, false)
                .switchIfEmpty(Mono.error(new DealNotFoundException(dealApi.getId())))
                .map(DealMapper::toDb)
                .flatMap(dealDb -> Flux.fromIterable(dealApi.getProducts())
                        .flatMap(newProduct -> {
                            ProductEntity productEntity = ProductMapper.toDb(newProduct);
                            productEntity.setDealId(dealDb.getId());
                            return productReactiveRepository
                                    .save(productEntity)
                                    .map(ProductMapper::toApi);
                        })
                        .collectList()
                        .map(savedProducts -> {
                            dealDb.setProducts(
                                    savedProducts.stream()
                                            .map(ProductMapper::toDb)
                                            .toList()
                            );
                            return dealDb;
                        })
                        .then(Mono.just(dealDb))
                )
                .map(DealMapper::toApi);
    }

    @Override
    public Mono<BigDecimal> getDealCost(UUID id) {
        return findById(id, null, true)
                .switchIfEmpty(Mono.error(new DealNotFoundException(id)))
                .map(DealMapper::toDb)
                .flatMap(dealDb -> Flux.fromIterable(dealDb.getProducts())
                        .map(ProductEntity::getCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
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
