package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Product;
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

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DealDbAdapter implements DealsSecondaryPort {

    private static final Sort DEFAULT_SORT = Sort.by(Sort.Order.by("lastModifiedDate"));

    private final DealReactiveRepository dealReactiveRepository;
    private final ProductReactiveRepository productReactiveRepository;

    @Override
    public Mono<Deal> findDealById(UUID dealId) {
        return dealReactiveRepository
                .findById(dealId)
                .map(DealMapper::toApi);
    }

    /**
     * Find all deals
     * @return Find all deals with the related objects loaded
     */
    @Override
    public Flux<Deal> findAll() {
        return dealReactiveRepository
                .findAll()
                .map(DealMapper::toApi)
                .flatMap(this::loadRelations);
    }

    /**
     * Create a new deal
     * @param deal Deal to be created
     *
     * @return the saved deal without the related entities
     */
    @Override
    @Transactional
    public Mono<Deal> createDeal(Deal deal) {
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
