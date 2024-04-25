package com.turysbay.UserPortalRestApp.repository;

import com.turysbay.UserPortalRestApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    News getNewsById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE News n SET n.title = :title, n.content = :content WHERE n.id = :id")
    void updateNews(@Param("title") String title, @Param("content") String content, @Param("id") Long id);

    List<News> findByCreatedBy(Long userId);
}
