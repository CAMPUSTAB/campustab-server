package com.campustab.feed.application.dto;

import com.campustab.feed.domain.Topic;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopicResponse {
    private Long id;
    private String name;

    public static TopicResponse from(Topic topic) {
        return TopicResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }
}
