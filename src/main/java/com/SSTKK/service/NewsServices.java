package com.SSTKK.service;

import com.SSTKK.model.NewsModel;
import com.SSTKK.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServices {
    @Autowired
    NewsRepository newsRepository;

    public List<NewsModel> getAllNews(){
        List<NewsModel> newsList = new ArrayList<>();
        newsRepository.findAll().forEach(newsModel -> newsList.add(newsModel));
        return newsList;
    }
    public boolean deleteNew(int id){
        newsRepository.deleteById(id);
        return newsRepository.findById(id) != null ? true: false;
    }
    public boolean addNew(NewsModel newsModel){
        MultipartFile pdfFile = newsModel.getPdfFile();
        if (pdfFile != null && !pdfFile.isEmpty()) {
            try {
                byte[] pdfContent = pdfFile.getBytes();
                newsModel.setPdfContent(pdfContent);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        NewsModel savedNewsModel = newsRepository.save(newsModel);
        return newsRepository.findById(savedNewsModel.getId()) != null ? true : false;
    }

    public NewsModel getNewsById(Integer id){
        return newsRepository.findById(id).get();
    }

    public void update(NewsModel news) {
        newsRepository.updateNewsWithId(news.getId(), news.getContent(), news.getTitle());
    }
}
