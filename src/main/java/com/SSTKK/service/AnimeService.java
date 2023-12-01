package com.SSTKK.service;

import com.SSTKK.model.AnimeModel;
import com.SSTKK.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {
    @Autowired
    AnimeRepository animeRepository;

    public List<AnimeModel> getAllAnimes(){
        List<AnimeModel> animeList = new ArrayList<>();
        animeRepository.findAll().forEach(animeModel -> animeList.add(animeModel));
        return animeList;
    }

    public AnimeModel getAnimeById(Long id){
        return animeRepository.findById(id).get();
    }

    public boolean saveOrUpdateAnime(AnimeModel anime){
        AnimeModel updateAnime = animeRepository.save(anime);
        return animeRepository.findById(updateAnime.getId()) != null ? true : false;
    }

    public boolean deleteAnime(Long id) {
        animeRepository.deleteById(id);
        return animeRepository.findById(id) != null ? true : false;
    }
}
