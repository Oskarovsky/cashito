package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.port.in.ProductManagementPort;
import com.slyko.cashitoapplication.port.out.ProductsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
import com.slyko.cashitodomain.domain.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class ProductService implements ProductManagementPort {

    private final ProductsSecondaryPort productsSecondaryPort;

    @Override
    public Flux<Product> getProducts() {
        return productsSecondaryPort.findAllProducts();
    }

    @Override
    public Mono<Product> getProduct(UUID id) {
        return productsSecondaryPort.findProductById(id);
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return productsSecondaryPort.createProduct(product);
    }
}
