package com.johnnyb.oktest.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    @Bean

    public OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(3, 10, TimeUnit.NANOSECONDS))
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC));
    }

    @Bean
    public Retrofit userRetrofit(@Value("${endpoint.user}") String endpointUser) {
        return new Retrofit.Builder()
                .baseUrl(endpointUser)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder().build())
                .build();
    }

    @Bean
    public Retrofit productRetrofit(@Value("${endpoint.product}") String endpointProduct) {
        return new Retrofit.Builder()
                .baseUrl(endpointProduct)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder().build())
                .build();
    }
}
