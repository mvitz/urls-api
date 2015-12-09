package com.innoq.urls.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

@SpringBootApplication
public class UrlsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlsApiApplication.class, args);
    }

    @Bean
    public HashFunction hashFunction() {
        return Hashing.murmur3_128();
    }

}
