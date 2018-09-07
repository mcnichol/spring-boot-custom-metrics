package com.pivotal.pa;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @Timed(value = "my-custom", histogram = true, description = "hell-yes")
    @RequestMapping("/")
    public ResponseEntity<String> myMetrics() {

        return new ResponseEntity<>("all-is-good", HttpStatus.OK);
    }
}
