package com.campustab.feed.presentation;

import com.campustab.feed.application.FeedService;
import com.campustab.feed.application.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    public ResponseEntity<Page<FeedResponse>> getFeeds(
            @PageableDefault(size = 20, sort = "publishedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(feedService.getAllFeeds(pageable));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Page<FeedResponse>> getFeedsByCategory(
            @PathVariable Long id,
            @PageableDefault(size = 20, sort = "published_at", direction = Sort.Direction.DESC) Pageable pageable) {
        // Native queries and some JPQL handle sorting differently.
        // For standard JPA, publishedAt is used. For categoryId, we'll assume sorting
        // works on Feed.
        return ResponseEntity.ok(feedService.getFeedsByCategory(id, pageable));
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Page<FeedResponse>> getFeedsByTopic(
            @PathVariable Long id,
            @PageableDefault(size = 20, sort = "published_at", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(feedService.getFeedsByTopic(id, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<FeedResponse>> searchFeeds(
            @RequestParam String q,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(feedService.searchFeeds(q, pageable));
    }
}
