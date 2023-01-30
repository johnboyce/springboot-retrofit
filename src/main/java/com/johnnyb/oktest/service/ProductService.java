package com.johnnyb.oktest.service;

import com.johnnyb.oktest.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import rx.Observable;

import java.io.IOException;

@Service
@CacheConfig(cacheNames = {"products"})
public class ProductService {

    public static final String PRODUCTS = "products";
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductClient productClient;

    public ProductService(Retrofit productRetrofit) {
        this.productClient = productRetrofit.create(ProductClient.class);
    }

    @Cacheable(value = PRODUCTS, key = "#productNumber", unless="#result == null")
    public Product lookupProduct(Integer productNumber) throws IOException {
        Observable<Product> product = productClient.getProduct(productNumber);
        Product single = product.toBlocking().single();
        return single;
    }
}
