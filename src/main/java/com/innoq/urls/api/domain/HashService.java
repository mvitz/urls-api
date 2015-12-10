package com.innoq.urls.api.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class HashService {

    private static final Logger LOGGER = LogManager.getLogger(HashService.class);

    private final Map<String, String> hashToValue = new HashMap<>();
    private final Hasher hasher;

    @Autowired
    public HashService(Hasher hasher) {
        this.hasher = hasher;
    }

    public String hash(String value) {
        final String hash = hasher.hash(value);
        LOGGER.info("'{}' hashed as '{}'", value, hash);
        hashToValue.put(hash, value);
        return hash;
    }

    public Optional<String> resolve(String hash) {
        final String value = hashToValue.get(hash);
        LOGGER.info("'{}' resolved to '{}'", hash, value);
        return Optional.ofNullable(value);
    }

}
