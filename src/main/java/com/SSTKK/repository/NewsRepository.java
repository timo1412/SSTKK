package com.SSTKK.repository;

import com.SSTKK.model.NewsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsModel,Integer> {
    @Modifying
    @Transactional
    @Query("update NewsModel n set n.title = :newTitle, n.content = :newContent where n.id = :newsId")
    void updateNewsWithId(@Param("newsId") Integer newsId, @Param("content") String newContent, @Param("title") String newTitle);

}
