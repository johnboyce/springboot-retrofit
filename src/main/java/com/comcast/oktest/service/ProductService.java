package com.comcast.oktest.service;

import com.comcast.oktest.model.Product;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Service
public class ProductService {
    private final ProductClient productClient;

    public ProductService(Retrofit productRetrofit) {
        this.productClient = productRetrofit.create(ProductClient.class);
    }

    public Product lookupProduct(Integer productNumber) throws IOException {
        Call<Product> product = productClient.getProduct(productNumber);
        Response<Product> execute = product.execute();
        return execute.body();
    }

}
