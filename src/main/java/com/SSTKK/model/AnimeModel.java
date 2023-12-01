package com.SSTKK.model;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "anime")
public class AnimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //zabezpeci generovanie jedinecnych idciek
    private Long id;
    @Column
    private String name;
    @Column
    private int year;

    public AnimeModel(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
