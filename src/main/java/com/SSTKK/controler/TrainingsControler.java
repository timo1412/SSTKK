package com.SSTKK.controler;
import com.SSTKK.model.TrainingModel;
import com.SSTKK.model.UsersModel;
import com.SSTKK.service.TrainingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
public class TrainingsControler {

    @Autowired
    TrainingService trainingService;

    public ArrayList<String> days = new ArrayList<>();
    @GetMapping("/trainings")
    public String getTrainnigs(Model model) {
        model.addAttribute("dny", Arrays.asList("Pondelok", "Utorok", "Streda", "Štvrtok", "Piatok"));
        model.addAttribute("casy", Arrays.asList("15:00", "16:00", "17:00", "18:00", "19:00", "20:00"));
        model.addAttribute("Treningy", trainingService.getAllTrainings());
        return "pages/trainings_page";
    }

    @PostMapping("/addTraining")
    public String addTreining(@RequestBody TrainingModel request, Model model,HttpSession session){
        UsersModel user = (UsersModel) session.getAttribute("user");
        if (!user.getRole().toString().equals("ADMIN")){
            model.addAttribute("Error_mess","Na tuto akciu nemate pravo, je potrebne byt prihlaseny ako admin");
            return "pages/error_page";
        }

        if (request.getDay().isEmpty()|| request.getTime().isEmpty() || request.getDescription().isEmpty()){
            model.addAttribute("Error_mess","Boli nespravne zadane udaje den,cas alebo popis treningu");
            return "pages/error_page";
        }
        trainingService.createTraining(request.getDay(),request.getTime(),request.getDescription());
        System.out.println(request.getDay() + " " + request.getTime() + " " + request.getDescription());
        return "redirect:/trainings";
    }

    @PostMapping("/deleteTraining/{id}")
    @ResponseBody
    public String deleteTraining(@PathVariable Integer id) {
        trainingService.deleteTraining(id);
        System.out.println("Idem mazat: " + id );
        return "redirect:/trainings";
    }
}
