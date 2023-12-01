package com.SSTKK.repository;

import com.SSTKK.model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsModel,Integer> {

}
