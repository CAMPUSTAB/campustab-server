package com.campustab.feed.application;

import com.campustab.feed.application.dto.CategoryResponse;
import com.campustab.feed.application.dto.TopicResponse;
import com.campustab.feed.infrastructure.CategoryRepository;
import com.campustab.feed.infrastructure.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TopicRepository topicRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> {
            List<TopicResponse> topics = topicRepository.findByCategoryId(category.getId())
                    .stream()
                    .map(TopicResponse::from)
                    .collect(Collectors.toList());
            return CategoryResponse.from(category, topics);
        }).collect(Collectors.toList());
    }

    public List<TopicResponse> getTopicsByCategory(Long categoryId) {
        return topicRepository.findByCategoryId(categoryId)
                .stream()
                .map(TopicResponse::from)
                .collect(Collectors.toList());
    }
}
