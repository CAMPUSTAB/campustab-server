package com.campustab.feed.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.feed.domain.FeedTopic;

public interface FeedTopicRepository extends JpaRepository<FeedTopic, Long> {
}
