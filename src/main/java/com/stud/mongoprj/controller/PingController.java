package com.stud.mongoprj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/pingtest")
public class PingController {
    @GetMapping
    public String pingerTest() {
        return "pingme " + new Date();
    }
}
