package com.campustab.user.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    private Long universityId;
    private Long departmentId;
    private List<Long> topicIds;
}
