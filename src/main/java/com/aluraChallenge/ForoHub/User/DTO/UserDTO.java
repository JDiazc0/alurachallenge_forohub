package com.aluraChallenge.ForoHub.User.DTO;

import com.aluraChallenge.ForoHub.Topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDTO(
        String name,
        String email,
        String password
) {
}
