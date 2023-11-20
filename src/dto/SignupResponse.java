package dto;

import entity.User;
import lombok.Builder;
import types.SignupStatus;

@Builder
public record SignupResponse (
    SignupStatus signupStatus,
    User user
) { }
