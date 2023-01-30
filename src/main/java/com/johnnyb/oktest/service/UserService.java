package com.johnnyb.oktest.service;

import com.johnnyb.oktest.model.User;
import com.johnnyb.oktest.model.UserApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import rx.Observable;

import java.io.IOException;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserService {
    public static final String USERS = "users";
    private final UserClient userService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(Retrofit userRetrofit) {
        this.userService = userRetrofit.create(UserClient.class);
    }

    @Cacheable(value = USERS, key = "#userid", unless="#result == null")
    public User lookupUser(long userid) throws IOException {
        Observable<UserApiResponse> user = userService.getUser(userid);
        UserApiResponse single = user.toBlocking().single();
        return single.getData();
    }
}
