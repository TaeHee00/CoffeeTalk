package dto;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record SignupRequest (
        String email,
        String password,
        String passwordConfirm,
        String name,
        String nickname,
        Timestamp birthday
) { }
