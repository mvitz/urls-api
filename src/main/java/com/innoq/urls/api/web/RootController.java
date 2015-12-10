package com.innoq.urls.api.web;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.innoq.urls.api.domain.HashService;

@RestController
@RequestMapping(value = "/",
        produces = MediaType.TEXT_PLAIN_VALUE)
public final class RootController {

    private static final Logger LOGGER = LogManager.getLogger(RootController.class);

    private final HashService hashService;

    @Autowired
    public RootController(HashService hashService) {
        this.hashService = hashService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> hash(@RequestBody String value) {
        LOGGER.trace("hash({})", value);
        final String hash = hashService.hash(value);
        return ResponseEntity.ok(hash);
    }

    @RequestMapping(value = "/{hash}", method = RequestMethod.GET)
    public ResponseEntity<?> resolve(@PathVariable String hash) {
        final Optional<String> value = hashService.resolve(hash);
        if (value.isPresent()) {
            return ResponseEntity.ok(value.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
