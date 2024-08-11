package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitodomain.port.in.ProductManagementPort;
import com.slyko.cashitodomain.port.out.ProductsSecondaryPort;
import com.slyko.cashitodomain.util.UseCase;
import reactor.core.publisher.Flux;

import java.util.UUID;

@UseCase
public class ProductService extends BaseService<Product, UUID> implements ProductManagementPort {

    private final ProductsSecondaryPort productsSecondaryPort;

    public ProductService(ProductsSecondaryPort productsSecondaryPort, ProductsSecondaryPort productsSecondaryPort1) {
        super(productsSecondaryPort);
        this.productsSecondaryPort = productsSecondaryPort1;
    }

    @Override
    public Flux<Product> getDealProducts(UUID dealId) {
        return productsSecondaryPort.getDealProducts(dealId);
    }
}
