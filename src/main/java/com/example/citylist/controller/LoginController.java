package com.example.citylist.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Login page requests handling
 */

//CrossOrigin for docker local tests and docker deployment
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping
    @Secured("ROLE_ALLOW_VIEW")
    @PreAuthorize("hasAuthority('ROLE_ALLOW_VIEW')")
    public String login(){
        return "authenticated successfully" ;
    }
}
