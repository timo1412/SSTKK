package com.SSTKK.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@Controller
public class RootController {
    @GetMapping("/")
    public String root(Model model) {
        return "pages/index";
    }
}
