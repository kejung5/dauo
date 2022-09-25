package com.dauo.project.domain.authenticate;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class LimitRateService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String apiKey) {
        Refill refill = Refill.intervally(10, Duration.ofSeconds(10));
        Bandwidth limit = Bandwidth.classic(1000, refill);
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

}