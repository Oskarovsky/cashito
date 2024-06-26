package com.slyko.cashitodomain;

import com.slyko.cashitodomain.port.in.ProductManagementPort;
import com.slyko.cashitodomain.port.out.ProductsSecondaryPort;
import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitodomain.util.UseCase;
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
