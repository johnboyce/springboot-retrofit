package com.johnnyb.oktest.service;

import com.johnnyb.oktest.model.Product;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ProductClient {

    @GET("/products/{number}")
    Observable<Product> getProduct(@Path("number") Integer number);

}
