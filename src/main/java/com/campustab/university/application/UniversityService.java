package com.campustab.university.application;

import com.campustab.university.application.dto.DepartmentResponse;
import com.campustab.university.application.dto.UniversityResponse;
import com.campustab.university.domain.Department;
import com.campustab.university.domain.University;
import com.campustab.university.infrastructure.DepartmentRepository;
import com.campustab.university.infrastructure.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final DepartmentRepository departmentRepository;

    public List<UniversityResponse> getAllUniversities() {
        return universityRepository.findAll().stream()
                .map(UniversityResponse::from)
                .collect(Collectors.toList());
    }

    public List<DepartmentResponse> getDepartmentsByUniversity(Long universityId) {
        if (!universityRepository.existsById(universityId)) {
            throw new IllegalArgumentException("University not found: " + universityId);
        }
        return departmentRepository.findByUniversityId(universityId).stream()
                .map(DepartmentResponse::from)
                .collect(Collectors.toList());
    }
}
