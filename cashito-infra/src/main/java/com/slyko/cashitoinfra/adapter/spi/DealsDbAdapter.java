package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.port.out.DealsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.entity.DealEntity;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DealsDbAdapter implements DealsSecondaryPort {

    private final DealReactiveRepository dealReactiveRepository;
    private final ProductReactiveRepository productReactiveRepository;

    @Override
    public Mono<Deal> findDealById(UUID dealId) {
        return null;
    }

    @Override
    public Mono<Deal> createDeal(Deal deal) {
        return dealReactiveRepository
                .save(DealEntity.toDb(deal))
                .flatMap(savedDeal -> Flux.fromIterable(deal.getProducts())
                        .flatMap(product -> {
                            ProductEntity productEntity = ProductEntity.toDb(product);
                            productEntity.setDealId(savedDeal.getId());
                            return productReactiveRepository
                                    .save(productEntity)
                                    .map(savedProduct -> new Product(product.id(), product.name(), product.type(), product.cost(), savedDeal.getId()));
                        })
                        .collectList()
                        .map(savedProducts -> {
                            savedDeal.setProducts(
                                    savedProducts.stream()
                                            .map(ProductEntity::toDb)
                                            .collect(Collectors.toList())
                            );
                            return savedDeal;
                        })
                        .then(Mono.just(savedDeal))
                )
                .map(DealEntity::toApi);
    }
}
