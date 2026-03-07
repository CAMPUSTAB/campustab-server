package com.campustab.user.application;

import com.campustab.feed.application.dto.TopicResponse;
import com.campustab.feed.domain.Topic;
import com.campustab.feed.infrastructure.TopicRepository;
import com.campustab.university.domain.Department;
import com.campustab.university.domain.University;
import com.campustab.university.infrastructure.DepartmentRepository;
import com.campustab.university.infrastructure.UniversityRepository;
import com.campustab.user.application.dto.UserResponse;
import com.campustab.user.application.dto.UserUpdateRequest;
import com.campustab.user.domain.User;
import com.campustab.user.domain.UserInterest;
import com.campustab.user.infrastructure.UserInterestRepository;
import com.campustab.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final DepartmentRepository departmentRepository;
    private final UserInterestRepository userInterestRepository;
    private final TopicRepository topicRepository;

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        List<TopicResponse> topics = userInterestRepository.findByUserId(userId).stream()
                .map(UserInterest::getTopic)
                .map(TopicResponse::from)
                .collect(Collectors.toList());

        return UserResponse.from(user, topics);
    }

    @Transactional
    public UserResponse updateUserProfile(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        if (request.getUniversityId() != null && request.getDepartmentId() != null) {
            University university = universityRepository.findById(request.getUniversityId())
                    .orElseThrow(() -> new IllegalArgumentException("University not found"));
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            user.updateUniversityAndDepartment(university, department);
        }

        if (request.getTopicIds() != null) {
            userInterestRepository.deleteByUserId(userId);
            List<Topic> newTopics = topicRepository.findAllById(request.getTopicIds());
            List<UserInterest> newInterests = newTopics.stream()
                    .map(topic -> UserInterest.builder().user(user).topic(topic).build())
                    .collect(Collectors.toList());
            userInterestRepository.saveAll(newInterests);
        }

        return getUserProfile(userId);
    }
}
