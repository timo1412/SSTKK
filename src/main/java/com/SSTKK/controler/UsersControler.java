package com.SSTKK.controler;
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

    @GetMapping("/users_page")
    public String getUsers(Model model){
        model.addAttribute("users",usersService.getAllUsers());
        return "pages/users_page";
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

    @PostMapping("/updateUser")
    public String updateTreining(@RequestBody UsersModel request, Model model, HttpSession session){
        UsersModel logUser = (UsersModel)session.getAttribute("user");
        if (!logUser.getRole().toString().equals("ADMIN")){
            model.addAttribute("Error_mess","Na tuto akciu nemate pravo, je potrebne byt prihlaseny ako admin");
            return "pages/error_page";
        }
        if (request.getEmail().isEmpty() || request.getLogin().isEmpty() || request.getPassword().isEmpty()){
            model.addAttribute("Error_mess","Niektore udaje su prazdne ziadne pole pouzivatela nesmie ostat prazdne");
            return "pages/error_page";
        }
        System.out.println("Idem upravovat" + request.getId());
        System.out.println("Login: " + request.getLogin() + " Email: " + request.getEmail() + " password: " + request.getPassword());
        usersService.updateUser(request);
        return "redirect:/trainings";
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

    @PostMapping("/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Integer id, HttpSession session,Model model) {
        UsersModel logUser = (UsersModel)session.getAttribute("user");
        if (!logUser.getRole().toString().equals("ADMIN")){
            model.addAttribute("Error_mess","Na tuto akciu nemate pravo, je potrebne byt prihlaseny ako admin");
            return "pages/error_page";
        }
        usersService.deleteUser(id);
        System.out.println("Idem mazat: " + id );
        return "redirect:/users_page";
    }
}
