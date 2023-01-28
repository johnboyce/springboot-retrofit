package com.comcast.oktest.service;

import com.comcast.oktest.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    @Cacheable(value = PRODUCTS, key = "#productNumber", unless="#result != null")
    public Product lookupProduct(Integer productNumber) throws IOException {
        Call<Product> product = productClient.getProduct(productNumber);
        Response<Product> execute = product.execute();
        return execute.body();
    }
}
