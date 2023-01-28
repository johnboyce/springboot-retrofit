# SpringBoot - Java 11 - OkHttp - Retrofit2 - RxJava - Cache 
The little sample project started out as an effort to checkout the latest OkHttp3 Library. It quickly turned into checking out some other libraries and this is the outcome.

![cover](https://t1.daumcdn.net/cfile/tistory/2503AD3956BBF48832?original)
## Contents
- [Introduction](#introduction)
- [Conclusions](#conclusions)
- [References](#references)

## Introduction
Upon looking into an issue I was experiencing with a specific version of OkHttp, I realized that Retrofit also bundles OkHttp3, so why not make my life easier and take the Retrofit route.
Since I needed to utilize a rest client, I also located a few free Rest JSON APIs for the obvious reasons. I started by using Enterprise IntelliJ IDEA's new project feature which allows for the creation of a new SpringBoot Project, which would be similar to using Springboot Initilizr.
With that, here are some of the high level decisions:
- SpringBoot
- Java 11
- Retrofit (bundles OkHttp3 but I have excluded for a new version)

As I got into it, I just kept going and added:
- Spring Cache
- Caffeine
- Lombok
- RxJava

It's worth noting that there is really no business logic to speak of in this project, just a demonstration of a few handy libraries. I did, however, create a few model classes to match the responses of the APIs I chose to integrate with.

## Conclusions

It works!
![happiness](https://media.tenor.com/w9t5gL4hnK0AAAAC/carlton-dance.gif)

## References

- [Spring Initializr](https://start.spring.io/)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp](https://square.github.io/okhttp/)
- [SpringBoot Caching](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#io.caching)
- [Caffeine](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#io.caching.provider.caffeine)
- [Lombok](https://projectlombok.org/)
- [rxJava](https://github.com/ReactiveX/RxJava)