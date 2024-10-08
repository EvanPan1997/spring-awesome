package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/process/{msg}")
    public String process(@PathVariable("msg") String msg)
    {
        log.info("server get request, msg: " + msg);
        return "process [" + msg + "] finished";
    }
}
