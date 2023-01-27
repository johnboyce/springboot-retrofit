package com.comcast.oktest.service;

import com.comcast.oktest.model.User;
import com.comcast.oktest.model.UserApiResponse;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;

@Service
public class UserService {
    private final UserClient userService;

    public UserService(Retrofit userRetrofit) {
        this.userService = userRetrofit.create(UserClient.class);
    }

    public User lookupUser(long id) throws IOException {
        Call<UserApiResponse> User = userService.getUser(id);
        Response<UserApiResponse> execute = User.execute();
        UserApiResponse body = execute.body();
        return Objects.requireNonNull(body).getData();
    }
    
}
