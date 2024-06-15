package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.port.in.DealPaymentPort;
import com.slyko.cashitoapplication.port.in.ProductManagementPort;
import com.slyko.cashitoapplication.port.out.DealsSecondaryPort;
import com.slyko.cashitoapplication.port.out.PaymentsSecondaryPort;
import com.slyko.cashitoapplication.port.out.ProductsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class FamilyStore implements DealPaymentPort, ProductManagementPort {

    private final DealsSecondaryPort dealsSecondaryPort;
    private final PaymentsSecondaryPort paymentsSecondaryPort;
    private final ProductsSecondaryPort productsSecondaryPort;

    public FamilyStore(DealsSecondaryPort dealsSecondaryPort,
                       PaymentsSecondaryPort paymentsSecondaryPort,
                       ProductsSecondaryPort productsSecondaryPort
    ) {
        this.dealsSecondaryPort = dealsSecondaryPort;
        this.paymentsSecondaryPort = paymentsSecondaryPort;
        this.productsSecondaryPort = productsSecondaryPort;
    }

    @Override
    public Mono<Deal> createDeal(Deal deal) {
        return dealsSecondaryPort.createDeal(deal);
    }

    @Override
    public Mono<Payment> payDeal(UUID dealId) {
        return null;
    }


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
        Mono<Product> savedProduct = productsSecondaryPort.createProduct(product);
        savedProduct.subscribe();

        return null;
    }
}
