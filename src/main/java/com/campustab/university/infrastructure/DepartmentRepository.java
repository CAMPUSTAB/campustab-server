package com.campustab.university.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.university.domain.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByUniversityId(Long universityId);
}
