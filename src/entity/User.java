package entity;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record User (
        String email,
        String password,
        String name,
        String nickname,
        Timestamp birthday
) {
}
