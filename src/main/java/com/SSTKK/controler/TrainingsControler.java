package com.SSTKK.controler;
import com.SSTKK.model.TrainingModel;
import com.SSTKK.service.TrainingService;
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
        model.addAttribute("dny", Arrays.asList("Pondelok", "Utorok", "Streda", "Stvrtok", "Piatok"));
        model.addAttribute("casy", Arrays.asList("15:00", "16:00", "17:00", "18:00", "19:00", "20:00"));
        model.addAttribute("Treningy", trainingService.getAllTrainings());
        return "pages/trainings_page";
    }

    @PostMapping("/addTraining")
    public String addTreining(@RequestBody TrainingModel request){
        if (request.getDay().isEmpty()){
            return "pages/error_page";
        }
        trainingService.createTraining(request.getDay(),request.getTime(),request.getDescription());
        System.out.println(request.getDay() + " " + request.getTime() + " " + request.getDescription());
        return "redirect:/trainings";
    }
}
