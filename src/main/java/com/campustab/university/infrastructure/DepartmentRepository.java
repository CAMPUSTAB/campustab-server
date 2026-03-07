package com.campustab.university.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.university.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
