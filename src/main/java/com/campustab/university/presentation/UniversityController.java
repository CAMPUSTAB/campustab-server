package com.campustab.university.presentation;

import com.campustab.university.application.UniversityService;
import com.campustab.university.application.dto.DepartmentResponse;
import com.campustab.university.application.dto.UniversityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<UniversityResponse>> getUniversities() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @GetMapping("/{id}/departments")
    public ResponseEntity<List<DepartmentResponse>> getDepartmentsByUniversity(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.getDepartmentsByUniversity(id));
    }
}
