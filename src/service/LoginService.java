package service;

import dto.LoginRequest;
import dto.LoginResponse;
import entity.User;
import repository.LoginRepository;
import types.LoginStatus;

import java.util.Optional;

public class LoginService {

    private static final LoginRepository loginRepository = new LoginRepository();

    public LoginResponse login(LoginRequest request) {
        Optional<User> findUser = loginRepository.findByEmail(request.email());

        if (findUser.isEmpty()) {
            // 입력한 Email을 가진 User가 없음
            return LoginResponse.builder()
                    .loginStatus(LoginStatus.ACCOUNT_NOT_FOUND)
                    .build();
        }

        User user = findUser.get();
        if (!user.password().equals(request.password())) {
            // 비밀번호가 일치하지 않을 경우
            return LoginResponse.builder()
                    .loginStatus(LoginStatus.PASSWORD_MISMATCH)
                    .build();
        }

        // 일치할 경우
        return LoginResponse.builder()
                .loginStatus(LoginStatus.LOGIN_SUCCESS)
                .user(user)
                .build();
    }
}
