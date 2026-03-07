package com.campustab.user.presentation;

import com.campustab.feed.application.dto.FeedResponse;
import com.campustab.global.security.CustomUserDetails;
import com.campustab.user.application.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<Page<FeedResponse>> getBookmarks(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(bookmarkService.getUserBookmarks(userDetails.getUser().getId(), pageable));
    }

    @PostMapping("/{feedId}")
    public ResponseEntity<Map<String, String>> addBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long feedId) {
        bookmarkService.addBookmark(userDetails.getUser().getId(), feedId);
        return ResponseEntity.ok(Map.of("status", "success"));
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<Map<String, String>> removeBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long feedId) {
        bookmarkService.removeBookmark(userDetails.getUser().getId(), feedId);
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}
