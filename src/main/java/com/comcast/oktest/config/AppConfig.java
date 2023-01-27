package com.comcast.oktest.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class AppConfig {

    @Bean

    public OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC));
    }

    @Bean
    public Retrofit userRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder().build())
                .build();
    }

    @Bean
    public Retrofit productRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder().build())
                .build();
    }
}
