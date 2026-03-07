package com.campustab.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.user.domain.UserInterest;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    List<UserInterest> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
