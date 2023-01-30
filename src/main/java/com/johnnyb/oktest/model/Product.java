package com.johnnyb.oktest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    private String id;
    private String title;
    private String description;
    private Double price;
    private String brand;
    private String category;
    private String thumbnail;
}
