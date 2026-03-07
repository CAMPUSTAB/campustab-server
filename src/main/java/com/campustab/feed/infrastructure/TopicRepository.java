package com.campustab.feed.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.feed.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
