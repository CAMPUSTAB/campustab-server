package com.campustab.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.user.domain.UserInterest;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
}
