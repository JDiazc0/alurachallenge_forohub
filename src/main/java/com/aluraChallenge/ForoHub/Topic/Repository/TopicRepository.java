package com.aluraChallenge.ForoHub.Topic.Repository;

import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.aluraChallenge.ForoHub.Topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatus(TopicStatus topicStatus, Pageable pageable);
}
