package com.aluraChallenge.ForoHub.Topic.Repository;

import com.aluraChallenge.ForoHub.Topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
