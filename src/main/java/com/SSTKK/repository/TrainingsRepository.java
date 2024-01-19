package com.SSTKK.repository;

import com.SSTKK.model.TrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingsRepository extends JpaRepository<TrainingModel,Integer> {

}
