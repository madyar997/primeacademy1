package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    @Modifying
    @Query("UPDATE Task t SET t.done = true WHERE t.id = :id")
    void markAsDone(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.user_id = :user_id")
    Iterable<Task> findAllByUserId(@Param("user_id") Long user_id);

    @Query("SELECT t FROM Task t WHERE t.user_id = :user_id AND t.id = :id")
    Task findByUserId(@Param("user_id") Long user_id, @Param("id") Long id);

}
