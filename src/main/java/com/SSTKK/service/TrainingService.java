package com.SSTKK.service;

import com.SSTKK.model.TrainingModel;
import com.SSTKK.repository.TrainingsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {

    private final TrainingsRepository trainingsRepository;

    public TrainingService(TrainingsRepository trainingsRepository){
        this.trainingsRepository = trainingsRepository;
    }

    public List<TrainingModel> getAllTrainings(){
        List<TrainingModel> trainings = new ArrayList<>();
        trainingsRepository.findAll().forEach(tr -> trainings.add(tr));
        return trainings;
    }

    public TrainingModel createTraining(String day, String time, String description){
        if (day == null && time == null){
            return null;
        }else {
            TrainingModel training = new TrainingModel();
            training.setTime(time);
            training.setDay(day);
            training.setDescription(description);
            return trainingsRepository.save(training);
        }
    }
}
