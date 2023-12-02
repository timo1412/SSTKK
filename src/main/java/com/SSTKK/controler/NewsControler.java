package com.SSTKK.controler;

import com.SSTKK.model.AnimeModel;
import com.SSTKK.model.NewsModel;
import com.SSTKK.service.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsControler {
    @Autowired
    NewsServices newsServices;
    @GetMapping("/newsList")
    public String viewNewsList(Model model){
        model.addAttribute("newsList",newsServices.getAllNews());
        return "pages/news_page";
    }
    @GetMapping("/addNews")
    public String getAddingPage() {
        return "pages/AddingNews_page";
    }

    @PostMapping("/addNews")
    public String AddNewNews(@ModelAttribute NewsModel newsModel){
        if (newsServices.addNew(newsModel)){
            return "redirect:/newsList";
        }
        return "pages/error_page";
    }
    @GetMapping("/editingNews/{id}")
    public String EditNews(@PathVariable Integer id, Model model){
        model.addAttribute("news",newsServices.getNewsById(id));
        return "pages/editingNews";
    }

}
