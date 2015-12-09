package com.innoq.urls.api.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;

@Component
public final class Hasher {

    private final HashFunction hashFunction;

    @Autowired
    public Hasher(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    public String hash(String value) {
        final HashCode hash = hashFunction.hashUnencodedChars(value);
        return String.format("%x", hash.asInt());
    }

}
