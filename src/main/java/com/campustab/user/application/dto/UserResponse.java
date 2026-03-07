package com.campustab.user.application.dto;

import com.campustab.feed.application.dto.TopicResponse;
import com.campustab.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private Long universityId;
    private String universityName;
    private Long departmentId;
    private String departmentName;
    private List<TopicResponse> interests;

    public static UserResponse from(User user, List<TopicResponse> interests) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .universityId(user.getUniversity() != null ? user.getUniversity().getId() : null)
                .universityName(user.getUniversity() != null ? user.getUniversity().getName() : null)
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .departmentName(user.getDepartment() != null ? user.getDepartment().getName() : null)
                .interests(interests)
                .build();
    }
}
