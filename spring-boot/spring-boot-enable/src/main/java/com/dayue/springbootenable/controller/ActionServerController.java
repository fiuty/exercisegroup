package com.dayue.springbootenable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dayue.enable.typetwo.service.ActionServer;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api/actionServer")
public class ActionServerController {

    @Autowired
    private ActionServer actionServer;

    @GetMapping("/actionType")
    public String actionType() {
        return actionServer.action();
    }
}
