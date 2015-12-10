package com.innoq.urls.api.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;

@Component
public final class Hasher {

    private static final Logger LOGGER = LogManager.getLogger(Hasher.class);

    private final HashFunction hashFunction;

    @Autowired
    public Hasher(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    public String hash(String value) {
        LOGGER.trace("hash({})", value);
        final HashCode hash = hashFunction.hashUnencodedChars(value);
        return String.format("%x", hash.asInt());
    }

}
