package com.SSTKK.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trainings_table")
public class TrainingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String day;
    String time;
    String description;

    @Override
    public String toString() {
        return "TreiningModel{" +
                "id=" + id +
                ", den='" + day + '\'' +
                ", cas='" + time + '\'' +
                ", popis='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
