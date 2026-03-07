package com.campustab.feed.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.feed.domain.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
