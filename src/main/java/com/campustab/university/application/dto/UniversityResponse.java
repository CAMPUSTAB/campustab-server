package com.campustab.university.application.dto;

import com.campustab.university.domain.University;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UniversityResponse {
    private Long id;
    private String name;
    private String domain;

    public static UniversityResponse from(University university) {
        return UniversityResponse.builder()
                .id(university.getId())
                .name(university.getName())
                .domain(university.getDomain())
                .build();
    }
}
