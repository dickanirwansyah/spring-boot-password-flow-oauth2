package com.spring.oauth2.springdemooauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerUser {

    @GetMapping(value = "/user")
    @PreAuthorize("PRIVILEGE_USER_READ")
    public String userAccess(){
        return "this is user access !";
    }
}
