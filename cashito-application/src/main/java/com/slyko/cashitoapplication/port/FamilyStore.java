package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.port.in.OrderingPaymentPort;
import com.slyko.cashitoapplication.port.in.ProductManagementPort;
import com.slyko.cashitoapplication.port.out.AccountsSecondaryPort;
import com.slyko.cashitoapplication.port.out.OrdersSecondaryPort;
import com.slyko.cashitoapplication.port.out.PaymentsSecondaryPort;
import com.slyko.cashitoapplication.port.out.ProductsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class FamilyStore implements OrderingPaymentPort, ProductManagementPort {

    private final OrdersSecondaryPort ordersSecondaryPort;
    private final PaymentsSecondaryPort paymentsSecondaryPort;
    private final ProductsSecondaryPort productsSecondaryPort;

    public FamilyStore(OrdersSecondaryPort ordersSecondaryPort,
                       PaymentsSecondaryPort paymentsSecondaryPort,
                       ProductsSecondaryPort productsSecondaryPort
    ) {
        this.ordersSecondaryPort = ordersSecondaryPort;
        this.paymentsSecondaryPort = paymentsSecondaryPort;
        this.productsSecondaryPort = productsSecondaryPort;
    }

    @Override
    public Mono<Order> placeOrder(Order order) {
        Mono<Order> savedOrder = ordersSecondaryPort.createOrder(order);
        savedOrder.subscribe();
        return null;
    }

    @Override
    public Mono<Payment> payOrder(UUID orderId) {
        var order = ordersSecondaryPort.findOrderById(orderId);

//        ordersSecondaryPort.save(order);

        return paymentsSecondaryPort.createPayment(new Payment(orderId, LocalDate.now()));
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
