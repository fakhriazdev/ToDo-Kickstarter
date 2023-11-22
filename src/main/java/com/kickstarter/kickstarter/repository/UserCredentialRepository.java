package com.kickstarter.kickstarter.repository;

import com.kickstarter.kickstarter.entity.User;
import com.kickstarter.kickstarter.entity.UserCredential;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends CrudRepository<UserCredential,String> {
    @Modifying
    @Query(value = "INSERT INTO user_credential (id,username,password,role_id) VALUES (:id,:username,:password,:role_id)",nativeQuery = true)
    void saveUserCredential(@Param("id") String id, @Param("username") String username, @Param("password") String password, @Param("role_id") String role_id);

    @Query(value = "SELECT * FROM user_credential WHERE username = :username", nativeQuery = true)
    Optional<UserCredential> findByUsername(@Param("username") String username);

}
