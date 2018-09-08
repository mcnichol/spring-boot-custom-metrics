package com.pivotal.pa;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private Counter counter;

    MyController(MeterRegistry meterRegistry) {

        counter = Counter
                .builder("my-custom-counter")
                .description("instance count")
                .tags("dev", "count")
                .register(meterRegistry);
    }

    @Timed(value = "my-custom-timer", histogram = true)
    @RequestMapping("/")
    public ResponseEntity<String> myMetrics() {
        counter.increment();

        return new ResponseEntity<>("done", HttpStatus.OK);
    }
}