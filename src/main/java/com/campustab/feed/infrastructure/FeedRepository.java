package com.campustab.feed.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.campustab.feed.domain.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query("SELECT f FROM Feed f JOIN FeedTopic ft ON f.id = ft.feed.id WHERE ft.topic.category.id = :categoryId")
    Page<Feed> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT f FROM Feed f JOIN FeedTopic ft ON f.id = ft.feed.id WHERE ft.topic.id = :topicId")
    Page<Feed> findByTopicId(@Param("topicId") Long topicId, Pageable pageable);

    @Query(value = "SELECT * FROM feeds WHERE search_vector @@ plainto_tsquery('simple', :keyword)", countQuery = "SELECT count(*) FROM feeds WHERE search_vector @@ plainto_tsquery('simple', :keyword)", nativeQuery = true)
    Page<Feed> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
