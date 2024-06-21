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
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DealDbAdapter implements DealsSecondaryPort {

    private final DealReactiveRepository dealReactiveRepository;
    private final ProductReactiveRepository productReactiveRepository;

    @Override
    public Mono<Deal> findDealById(UUID dealId) {
        return dealReactiveRepository
                .findById(dealId)
                .map(DealMapper::toApi);
    }

    @Override
    public Flux<Deal> findAll() {
        return dealReactiveRepository
                .findAll()
                .map(DealMapper::toApi);
    }

    @Override
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
                                            .collect(Collectors.toList())
                            );
                            return savedDeal;
                        })
                        .then(Mono.just(savedDeal))
                )
                .map(DealMapper::toApi);

    }
}
