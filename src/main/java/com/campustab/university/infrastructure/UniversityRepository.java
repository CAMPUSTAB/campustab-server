package com.campustab.university.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.university.domain.University;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
