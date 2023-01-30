package com.johnnyb.oktest.service;

import com.johnnyb.oktest.model.UserApiResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UserClient {

    @GET("/api/users/{id}")
    Observable<UserApiResponse> getUser(@Path("id") long id);
}
