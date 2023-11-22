package com.kickstarter.kickstarter.repository;

import com.kickstarter.kickstarter.entity.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends CrudRepository<Status,String> {
    @Query(value = "SELECT * FROM m_status WHERE status = :status", nativeQuery = true)
    Optional<Status> findByName(@Param("status") String status);
}
