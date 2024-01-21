package com.SSTKK.repository;
import com.SSTKK.model.UsersModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<UsersModel,Integer> {
    Optional<UsersModel> findByLoginAndPassword(String login, String password);
    Optional<UsersModel> findFirstByLogin(String login);
    @Modifying
    @Transactional
    @Query("update UsersModel n set n.login = :newLogin, n.password = :newPassword, n.email = :newEmail where n.id = :userId")
    void updateUserWithId(@Param("userId") Integer newsId, @Param("newPassword") String password, @Param("newLogin") String newLogin, @Param("newEmail") String newEmail);
}

