package com.comcast.oktest.service;

import com.comcast.oktest.model.Product;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ProductClient {

    @GET("/products/{number}")
    Observable<Product> getProduct(@Path("number") Integer number);

}
