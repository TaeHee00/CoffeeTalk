package service;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.SignupRequest;
import dto.SignupResponse;
import entity.User;
import repository.SignupRepository;
import types.SignupStatus;

import java.util.Optional;

public class SignupService {

    private static final SignupRepository signupRepository = new SignupRepository();

    public SignupResponse signup(SignupRequest request) {
        Optional<User> findUser = signupRepository.findByEmail(request.email());

        if (findUser.isPresent()) {
            // 이미 사용중인 이메일
            return SignupResponse.builder()
                    .signupStatus(SignupStatus.EMAIL_ALREADY_EXISTS)
                    .build();
        }

        if (!request.password().equals(request.passwordConfirm())) {
            // 비밀번호가 일치하지 않을 경우
            return SignupResponse.builder()
                    .signupStatus(SignupStatus.PASSWORD_CONFIRMATION_MISMATCH)
                    .build();
        }


        // 모든 조건을 만족할 경우
        User user = User.builder()
                .email(request.email())
                .password(request.password())
                .name(request.name())
                .nickname(request.nickname())
                .birthday(request.birthday())
                .build();
        signupRepository.save(user);

        return SignupResponse.builder()
                .signupStatus(SignupStatus.SIGNUP_SUCCESS)
                .user(user)
                .build();
    }
}
