package com.johnnyb.oktest.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public Retrofit userRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder().build())
                .build();
    }

    @Bean
    public Retrofit productRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder().build())
                .build();
    }
}
