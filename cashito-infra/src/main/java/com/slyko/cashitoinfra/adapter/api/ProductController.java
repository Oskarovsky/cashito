package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.port.in.ProductManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.OrderRequest;
import com.slyko.cashitoinfra.adapter.api.dto.OrderResponse;
import com.slyko.cashitoinfra.adapter.api.dto.ProductRequest;
import com.slyko.cashitoinfra.adapter.api.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/productTest")
@RequiredArgsConstructor
public class ProductController {

    private final ProductManagementPort productManagementPort;

    @PostMapping
    public Mono<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Mono<Product> product = productManagementPort.createProduct(request.toDomain());
        ResponseEntity<Mono<Product>> monoResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        System.out.println(monoResponseEntity);
        return null;
    }


}
