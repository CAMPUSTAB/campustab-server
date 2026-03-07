package com.campustab.feed.presentation;

import com.campustab.feed.application.CategoryService;
import com.campustab.feed.application.dto.CategoryResponse;
import com.campustab.feed.application.dto.TopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}/topics")
    public ResponseEntity<List<TopicResponse>> getTopicsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getTopicsByCategory(id));
    }
}
