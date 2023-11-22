package com.kickstarter.kickstarter.repository;

import com.kickstarter.kickstarter.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends CrudRepository<Task,String> {

    @Modifying
    @Query(value = "INSERT INTO m_task (id,title,status_id,user_id) VALUES (:id,:title,:status_id,:user_id)",nativeQuery = true)
    void saveTask(@Param("id") String id, @Param("title") String title, @Param("status_id") String status,@Param("user_id") String user);


    @Modifying
    @Query(value = "UPDATE m_task SET title = :title ,status_id = :status_id, user_id = :user_id WHERE id = :id", nativeQuery = true)
    void updateTask(@Param("id") String id, @Param("title") String title, @Param("status_id") String status,@Param("user_id") String user);
}
