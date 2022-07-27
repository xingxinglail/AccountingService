package com.github.accounting.controller;

import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public String hello () {
        throw new ResourceNotFoundException("找不到");
    }

    @GetMapping("/p")
    public String hello2 () {
        throw new InvalidParameterException("参数不正确");
    }

    @GetMapping("/o")
    public String hello3 () {
        throw new IllegalArgumentException("参数不正确??");
    }
}
