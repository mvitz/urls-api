package com.innoq.urls.api.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.innoq.urls.api.domain.HashService;

@RestController
@RequestMapping(value = "/",
        consumes = MediaType.TEXT_PLAIN_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
public final class RootController {

    private final HashService hashService;

    @Autowired
    public RootController(HashService hashService) {
        this.hashService = hashService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> hash(@RequestBody String value) {
        final String hash = hashService.hash(value);
        return ResponseEntity.ok(hash);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> resolve(@RequestBody String hash) {
        final Optional<String> value = hashService.resolve(hash);
        if (value.isPresent()) {
            return ResponseEntity.ok(value.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
