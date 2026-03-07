package com.campustab.feed.application;

import com.campustab.feed.application.dto.FeedResponse;
import com.campustab.feed.infrastructure.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedService {

    private final FeedRepository feedRepository;

    public Page<FeedResponse> getAllFeeds(Pageable pageable) {
        return feedRepository.findAll(pageable).map(FeedResponse::from);
    }

    public Page<FeedResponse> getFeedsByCategory(Long categoryId, Pageable pageable) {
        return feedRepository.findByCategoryId(categoryId, pageable).map(FeedResponse::from);
    }

    public Page<FeedResponse> getFeedsByTopic(Long topicId, Pageable pageable) {
        return feedRepository.findByTopicId(topicId, pageable).map(FeedResponse::from);
    }

    public Page<FeedResponse> searchFeeds(String keyword, Pageable pageable) {
        return feedRepository.searchByKeyword(keyword, pageable).map(FeedResponse::from);
    }
}
