package com.SSTKK.controler;

import com.SSTKK.model.UsersModel;
import com.SSTKK.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersControler {
    private final UsersService usersService;

    public UsersControler(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String root() {
        return "pages/index";
    }

    @GetMapping("/trainings")
    public String getTableTrainings(){
        return "pages/trainings_page";
    }

    @GetMapping("/error_page")
    public String getErrorPage(Model model){
        model.addAttribute("errorRequest",new UsersModel());
        return "pages/error_page";
    }

    @GetMapping("/personal_page")
    public String getPersonalPage(){
//        model.addAttribute("personalRequest", new UsersModel());
        return "pages/personal_page";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest",new UsersModel());
        return "pages/register_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UsersModel());
        return "pages/login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request:  " + usersModel);
        UsersModel registeredUser = usersService.registrationuser(usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return registeredUser == null ? "pages/error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request:  " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        if (authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
            return "pages/personal_page";
        }else {
            return "pages/error_page";
        }
    }
}
