package com.SSTKK.service;

import com.SSTKK.model.NewsModel;
import com.SSTKK.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        NewsModel savedNewsModel = newsRepository.save(newsModel);
        return newsRepository.findById(savedNewsModel.getId()) != null ? true : false;
    }

    public NewsModel getNewsById(Integer id){
        return newsRepository.findById(id).get();
    }
}
