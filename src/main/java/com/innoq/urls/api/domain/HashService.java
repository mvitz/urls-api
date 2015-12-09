package com.innoq.urls.api.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class HashService {

    private final Map<String, String> hashToValue = new HashMap<>();
    private final Hasher hasher;

    @Autowired
    public HashService(Hasher hasher) {
        this.hasher = hasher;
    }

    public String hash(String value) {
        final String hash = hasher.hash(value);
        hashToValue.put(hash, value);
        return hash;
    }

    public Optional<String> resolve(String hash) {
        final String value = hashToValue.get(hash);
        return Optional.ofNullable(value);
    }

}
