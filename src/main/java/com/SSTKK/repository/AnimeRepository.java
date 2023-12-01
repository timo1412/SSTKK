package com.SSTKK.repository;

import com.SSTKK.model.AnimeModel;
import com.SSTKK.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeModel,Long> {

}
