package com.slyko.cashitosimulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deal {
    private String id;
    private Long version;
    private String title;
    private String status;
    private String accountId;
    private List<Product> products;
}