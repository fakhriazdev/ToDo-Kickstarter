package com.kickstarter.kickstarter.repository;

import com.kickstarter.kickstarter.entity.Role;
import com.kickstarter.kickstarter.entity.UserCredential;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role,String> {
    @Query(value = "SELECT * FROM m_role WHERE name = :name", nativeQuery = true)
    Optional<Role> findByName(@Param("name") String name);

    @Modifying
    @Query(value = "INSERT INTO m_role (id,name) VALUES (:id,:name)",nativeQuery = true)
    void saveRole(@Param("id") String id, @Param("name") String name);
}
