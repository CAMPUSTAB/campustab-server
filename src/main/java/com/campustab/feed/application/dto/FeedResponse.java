package com.campustab.feed.application.dto;

import com.campustab.feed.domain.Feed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedResponse {
    private Long id;
    private String title;
    private String summary;
    private String contentUrl;
    private String imageUrl;
    private String sourceName;
    private LocalDateTime publishedAt;

    public static FeedResponse from(Feed feed) {
        return FeedResponse.builder()
                .id(feed.getId())
                .title(feed.getTitle())
                .summary(feed.getSummary())
                .contentUrl(feed.getContentUrl())
                .imageUrl(feed.getImageUrl())
                .sourceName(feed.getSource() != null ? feed.getSource().getName() : null)
                .publishedAt(feed.getPublishedAt())
                .build();
    }
}
