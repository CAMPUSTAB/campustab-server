package com.campustab.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.user.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
