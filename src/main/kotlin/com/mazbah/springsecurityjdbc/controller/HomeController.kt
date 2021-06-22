package com.mazbah.springsecurityjdbc.controller

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping





@RestController
class HomeController() {

    @GetMapping("/")
    fun home():String{
        return ("<h1> Welcome </h1>")
    }

    @GetMapping("/user")
    fun user():String{
        return ("<h1> Welcome User </h1>")
    }

    @GetMapping("/admin")
    fun admin():String{
        return ("<h1> Welcome Admin</h1>")
    }

}