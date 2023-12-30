package com.SSTKK.controler;
import com.SSTKK.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.SSTKK.model.UsersModel;
import com.SSTKK.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UsersControler {
    private final UsersService usersService;

    public UsersControler(UsersService usersService) {
        this.usersService = usersService;
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
    public String getPersonalPage(HttpSession session){
        UsersModel user = (UsersModel) session.getAttribute("user");
        if (user != null){
            return "pages/personal_page";
        }
        return "pages/error_page";
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
        if (registeredUser.getEmail().isEmpty() || registeredUser.getLogin().isEmpty() || registeredUser.getPassword().isEmpty()){
            return "pages/error_page";
        }
        return registeredUser == null ? "pages/error_page" : "redirect:/user/login";
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model,HttpSession session){
        System.out.println("login request:  " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        if (authenticated != null &&
                (authenticated.getLogin() != null || !authenticated.getLogin().isEmpty())
        ){
            session.setAttribute("user", authenticated);
            model.addAttribute("userLogin", authenticated.getLogin());
            return "redirect:/";
        }else {
            return "pages/error_page";
        }
    }
}
