package com.SSTKK.controler;
import com.SSTKK.model.NewsModel;
import com.SSTKK.service.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


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

    @GetMapping("/downloadPdf")
    public ResponseEntity<byte[]> downloadPdf(@RequestParam("id") int id) {
        NewsModel news = newsServices.getNewsById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "file.pdf");  // Názov súboru

        return new ResponseEntity<>(news.getPdfContent(), headers, HttpStatus.OK);
    }
    @PostMapping("/addNews")
    public String AddNewNews(@ModelAttribute NewsModel newsModel,
                             @RequestParam("pdfFile") MultipartFile pdfFile,Model model){

        newsModel.setPdfFile(pdfFile);
        if (newsModel.getCreator().isEmpty() || newsModel.getTitle().isEmpty() || newsModel.getContent().isEmpty()){
            model.addAttribute("Error_mess","Dojebal si to");
            return "pages/error_page";
        }
        if (newsServices.addNew(newsModel)){
            return "redirect:/newsList";
        }
        return "pages/error_page";
    }
    @PostMapping("editNews")
    public String editNews(@ModelAttribute NewsModel newsModel){
        if (newsModel.getContent().isEmpty() || newsModel.getTitle().isEmpty() || newsModel.getCreator().isEmpty()){
            return "pages/error_page";
        }
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
