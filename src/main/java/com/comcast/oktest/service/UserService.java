package com.comcast.oktest.service;

import com.comcast.oktest.model.User;
import com.comcast.oktest.model.UserApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;

@Service
public class UserService {
    public static final String USERS = "users";
    private final UserClient userService;
    private final Logger logger =  LoggerFactory.getLogger(UserService.class);

    public UserService(Retrofit userRetrofit) {
        this.userService = userRetrofit.create(UserClient.class);
    }
    @Cacheable(value = USERS, key = "#userid")
    public User lookupUser(long userid) throws IOException {
        Call<UserApiResponse> User = userService.getUser(userid
        );
        Response<UserApiResponse> execute = User.execute();
        UserApiResponse body = execute.body();
        return Objects.requireNonNull(body).getData();
    }

    @CacheEvict(value = USERS, allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.users.ttl}")
    public void evictUsersCache() {
        logger.info("evicting {} cache", USERS);
    }

}
