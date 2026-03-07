package com.campustab.user.application;

import com.campustab.feed.application.dto.FeedResponse;
import com.campustab.feed.domain.Feed;
import com.campustab.feed.infrastructure.FeedRepository;
import com.campustab.user.domain.Bookmark;
import com.campustab.user.domain.User;
import com.campustab.user.infrastructure.BookmarkRepository;
import com.campustab.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    public Page<FeedResponse> getUserBookmarks(Long userId, Pageable pageable) {
        return bookmarkRepository.findByUserId(userId, pageable)
                .map(Bookmark::getFeed)
                .map(FeedResponse::from);
    }

    @Transactional
    public void addBookmark(Long userId, Long feedId) {
        if (bookmarkRepository.findByUserIdAndFeedId(userId, feedId).isEmpty()) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            Feed feed = feedRepository.findById(feedId)
                    .orElseThrow(() -> new IllegalArgumentException("Feed not found"));

            bookmarkRepository.save(Bookmark.builder().user(user).feed(feed).build());
        }
    }

    @Transactional
    public void removeBookmark(Long userId, Long feedId) {
        bookmarkRepository.findByUserIdAndFeedId(userId, feedId)
                .ifPresent(bookmarkRepository::delete);
    }
}
