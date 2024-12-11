package com.aluraChallenge.ForoHub.User.DTO;

import com.aluraChallenge.ForoHub.User.User;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {
    public UserResponseDTO(User user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
