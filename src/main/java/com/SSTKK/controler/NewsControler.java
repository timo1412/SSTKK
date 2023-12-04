package com.SSTKK.controler;

import com.SSTKK.model.NewsModel;
import com.SSTKK.service.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("editNews")
    public String editNews(@ModelAttribute NewsModel newsModel){
        newsServices.update(newsModel);
        return "redirect:/newsList";
    }
    @GetMapping("/editNews")
    public String EditNews(@RequestParam("id") Integer id, Model model){
        NewsModel news = newsServices.getNewsById(id);
        model.addAttribute("news", news);
        return "pages/editNews_page";
    }

    @GetMapping("deleteNews")
    public String getDeleteNewsPage(@RequestParam("id") Integer id, Model model){
        NewsModel news = newsServices.getNewsById(id);
        model.addAttribute("news", news);
        return "pages/deleteNews_page";
    }
    @PostMapping("deleteNews")
    public String deleteNews(@ModelAttribute NewsModel newsModel){
        System.out.println(newsModel.getId());
        if (newsServices.deleteNew(newsModel.getId())){
            return "redirect:/newsList";
        }
        return "pages/error_page";
    }
}
