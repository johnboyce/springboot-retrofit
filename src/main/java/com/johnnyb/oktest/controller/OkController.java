package com.johnnyb.oktest.controller;

import com.johnnyb.oktest.model.Product;
import com.johnnyb.oktest.model.User;
import com.johnnyb.oktest.service.ProductService;
import com.johnnyb.oktest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class OkController {
    private final UserService userRepository;
    private final ProductService productService;

    public OkController(UserService userRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @GetMapping("/user")
    public User getRandomUser() throws IOException {
        return userRepository.lookupUser(getRandomNumber());
    }

    @GetMapping("/product")
    public Product getProduct() throws IOException {
        return productService.lookupProduct(getRandomNumber());
    }

    private int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }
}
