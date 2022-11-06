package com.halfsay.flower.controller;

import com.halfsay.flower.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class TestController {
    private final RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/flower/{string}", method = RequestMethod.GET)
    public Result echo(@PathVariable String string) {
        return restTemplate.getForObject("http://info/flower/" + string, Result.class);
    }
}
