package com.campustab.feed.application.dto;

import com.campustab.feed.domain.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private List<TopicResponse> topics;

    public static CategoryResponse from(Category category, List<TopicResponse> topics) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .topics(topics)
                .build();
    }
}
