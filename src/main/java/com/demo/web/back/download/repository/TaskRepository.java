package com.demo.web.back.download.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.repository.BaseRepository;
import com.demo.web.back.download.entity.Task;
import com.demo.web.back.download.enums.Pace;


@Repository
public interface TaskRepository extends BaseRepository<Task, Long> {
    
    @Modifying(clearAutomatically = true)
    @Query("update Task o set o.pace = :pace , o.userSecond = :userSecond  where  o.id = :id")
    public void updatePaceAndUserSecond(@Param("pace") Pace pace, @Param("id") Long id, @Param("userSecond") String userSecond);
    
    @Modifying(clearAutomatically = true)
    @Query("update Task o set o.pace = :pace   where  o.id = :id")
    public void updatePace(@Param("pace") Pace pace, @Param("id") Long id);
    
    public void clear();
}
