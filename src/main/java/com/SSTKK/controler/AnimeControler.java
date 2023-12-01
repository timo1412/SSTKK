package com.SSTKK.controler;

import com.SSTKK.model.AnimeModel;
import com.SSTKK.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnimeControler {
    @Autowired
    AnimeService animeService;
    @GetMapping("/animeList")
    public String viewAnimeList(Model model){
        List<AnimeModel> animes = new ArrayList<>();
        animes.add(new AnimeModel());
        animes.add(new AnimeModel());
        animes.add(new AnimeModel());
        animes.add(new AnimeModel());
        model.addAttribute("animeList",animeService.getAllAnimes());
        //model.addAttribute("animeList",animes);
        return "pages/anime_page";
    }

    @GetMapping("/saveAnime")
    public String addAnime(Model model){
        model.addAttribute("anime" , new AnimeModel());
        return "pages/anime_page";
    }
    @PostMapping("/saveAnime")
    public String saveAnime(@ModelAttribute AnimeModel anime){
        if (animeService.saveOrUpdateAnime(anime)){
            return "redirect:/animeList";
        }
        return "error/page";
    }
    @GetMapping("/editAnime/{id}")
    public String editAnime(@PathVariable Long id, Model model){
        model.addAttribute("anime",animeService.getAnimeById(id));
        return "EditAnime";
    }
    @PostMapping("/editSaveanime")
    public String editSaveAnime(AnimeModel anime, RedirectAttributes redirectAttributes){
        if (animeService.saveOrUpdateAnime(anime)){
            redirectAttributes.addFlashAttribute("message","Save Success");
            return "redirect:/viewAnileList";
        }
        redirectAttributes.addFlashAttribute("message","Save Failure");
        return "redirect:/editAnime" + anime.getId();
    }
    @GetMapping("/deleteAnime/{id}")
    public String deleteAnime(Long id, RedirectAttributes redirectAttributes){
        if (animeService.deleteAnime(id)){
            redirectAttributes.addFlashAttribute("message","Delete Success");
        }
        redirectAttributes.addFlashAttribute("message","Delete Failure");
        return "redirect:/addAnime";
    }
    @PostMapping("/deleteAnime")
    public String deleteAnime(Long id){
        if (animeService.deleteAnime(id)){
            return "redirect:/animeList";
        }
        return "error/page";
    }
}
