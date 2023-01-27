package com.comcast.oktest.service;

import com.comcast.oktest.model.UserApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserClient {

    @GET("/api/users/{id}")
    Call<UserApiResponse> getUser(@Path("id") long id);
}
