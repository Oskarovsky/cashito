package com.slyko.cashitoapplication.gateway;

import com.slyko.cashitoapplication.request.ProductRequest;
import com.slyko.cashitodomain.port.in.ProductManagementPort;
import com.slyko.cashitodomain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductManagementPort productManagementPort;

    @GetMapping
    public ResponseEntity<Flux<Product>> getProducts() {
        Flux<Product> productFlux = productManagementPort.getAll();
        return new ResponseEntity<>(productFlux, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Product>> getProduct(@PathVariable(value = "id") UUID id) {
        Mono<Product> productMono = productManagementPort.getById(id, null, false);
        return new ResponseEntity<>(productMono, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Product>> createProduct(@RequestBody ProductRequest request) {
        Mono<Product> product = productManagementPort.create(request.toDomain());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable UUID id) {
        return productManagementPort
                .deleteById(id, null)
                .then(Mono.fromCallable(() -> noContent().build()));
    }

}
