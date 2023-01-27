package com.comcast.oktest.service;

import com.comcast.oktest.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ProductClient {

    @GET("/products/{number}")
    Call<Product> getProduct(@Path("number") Integer number);

}
