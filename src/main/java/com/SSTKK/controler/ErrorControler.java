package com.SSTKK.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControler {

    @GetMapping("/error_page")
    public String viewNewsList(){
        return "pages/error_page";
    }

}
