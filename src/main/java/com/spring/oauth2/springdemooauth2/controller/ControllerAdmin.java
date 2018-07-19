package com.spring.oauth2.springdemooauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAdmin {

    @GetMapping(value = "/admin")
    @PreAuthorize("PRIVILEGE_ADMIN_READ")
    public String adminAccess(){
        return "admin level success !";
    }
}
