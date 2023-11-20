package controller;

import dto.LoginRequest;
import dto.LoginResponse;
import entity.User;
import lombok.Setter;
import service.LoginService;
import types.LoginStatus;
import view.LoginPage;
import view.MenuPage;
import view.SignupPage;

import javax.swing.*;
import java.util.Optional;

public class LoginController {

    private static final LoginService loginService = new LoginService();
    @Setter
    private static User loggedInUser = null;

    public void login(LoginPage frame) {
        if (!validate(frame)) return;

        String email = frame.userText.getText();
        char[] passwordChars = frame.passwordText.getPassword();
        String password = new String(passwordChars);

        LoginRequest request = LoginRequest.builder()
                                        .email(email)
                                        .password(password)
                                        .build();

        LoginResponse response = loginService.login(request);

        // 로그인 상태별 Alert 창
        switch (response.loginStatus()) {
            case ACCOUNT_NOT_FOUND -> JOptionPane.showMessageDialog(null, "존재하지 않는 계정입니다.");
            case PASSWORD_MISMATCH -> JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
            case LOGIN_SUCCESS -> JOptionPane.showMessageDialog(null, "로그인 성공!");
        }

        // 로그인 성공이 아니면 탈출
        if (response.loginStatus() != LoginStatus.LOGIN_SUCCESS) return;

        loggedInUser = response.user();

        // 기존 로그인 페이지 삭제 후 메인 화면 띄우기
        frame.dispose();
        new MenuPage();
    }

    public void signupPage(LoginPage frame) {
        frame.dispose();
        new SignupPage();
    }

    public static Optional<User> getLoggedInUser() {
        return Optional.ofNullable(loggedInUser);
    }

    private boolean validate(LoginPage frame) {
        String email = frame.userText.getText();
        char[] passwordChars = frame.passwordText.getPassword();
        String password = new String(passwordChars);

        if (email.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
            return false;
        }
        if (password.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
            return false;
        }

        return true;
    }
}
