package com.SSTKK.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users_table")
public class UsersModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String login;
    String password;
    String email;
    Role role;
    @OneToMany(fetch = FetchType.EAGER)
    List<NewsModel> addNews = new ArrayList<>();

    public List<NewsModel> getAddNews() {
        return addNews;
    }

    public void setAddNews(List<NewsModel> addNews) {
        this.addNews = addNews;
    }

    public enum Role {
        USER, ADMIN
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersModel userModel = (UsersModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(login, userModel.login) && Objects.equals(password, userModel.password) && Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
