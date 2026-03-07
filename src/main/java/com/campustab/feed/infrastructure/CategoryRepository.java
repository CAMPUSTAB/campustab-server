package com.campustab.feed.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.feed.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
