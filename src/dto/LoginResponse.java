package dto;

import entity.User;
import lombok.Builder;
import types.LoginStatus;

@Builder
public record LoginResponse (
        LoginStatus loginStatus,
        User user
) {
}
