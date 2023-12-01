package com.SSTKK.repository;

import com.SSTKK.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel,Integer> {
    Optional<UsersModel> findByLoginAndPassword(String login, String password);
    Optional<UsersModel> findFirstByLogin(String login);
}
