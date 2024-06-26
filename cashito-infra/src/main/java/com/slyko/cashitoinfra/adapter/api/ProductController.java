package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.port.in.ProductManagementPort;
import com.slyko.cashitodomain.domain.Product;
import com.slyko.cashitoinfra.adapter.api.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductManagementPort productManagementPort;

    @GetMapping
    public ResponseEntity<Flux<Product>> getProducts() {
        Flux<Product> productFlux = productManagementPort.getProducts();
        return new ResponseEntity<>(productFlux, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Product>> getProduct(@PathVariable(value = "id") UUID id) {
        Mono<Product> productMono = productManagementPort.getProduct(id);
        return new ResponseEntity<>(productMono, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Product>> createProduct(@RequestBody ProductRequest request) {
        Mono<Product> product = productManagementPort.createProduct(request.toDomain());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
