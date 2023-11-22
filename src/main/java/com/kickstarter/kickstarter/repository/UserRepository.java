package com.kickstarter.kickstarter.repository;

import com.kickstarter.kickstarter.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    @Modifying
    @Query(value = "INSERT INTO m_user (id,name,phone_number,user_credential_id) VALUES (:id,:name,:phone_number,:user_credential_id)",nativeQuery = true)
    void saveUser(@Param("id") String id, @Param("name") String name, @Param("phone_number") String phone_number,@Param("user_credential_id") String user_credential_id);
}
