package com.research.microservice_experiment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/status")
    public String checkStatus() {
        return "Microservice is Active & Ready.";
    }
}