package com.aluraChallenge.ForoHub.User.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicUserDTO(
        Long id
) {
}
