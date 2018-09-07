package com.pivotal.pa;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.search.Search;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    private MeterRegistry meterRegistry;

    MyController(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;

        meterRegistry.getMeters();

        ArrayList<Object> list = new ArrayList<>(4);

        Counter
                .builder("my-counter")
                .description("indicates instance count of the object")
                .tags("dev", "performance")
                .register(meterRegistry);

        Gauge.builder("my-custom-cache", list, List::size).tag("dev","performance").register(meterRegistry);
    }

    @Timed(value = "my-custom-timer", histogram = true, description = "hell-yes")
    @RequestMapping("/")
    public ResponseEntity<String> myMetrics() {
        Counter counter = meterRegistry.find("my-counter").counter();
        counter.increment();

        return new ResponseEntity<>("all-is-good", HttpStatus.OK);
    }
}