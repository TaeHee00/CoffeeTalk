package controller;

import dto.LoginRequest;
import dto.LoginResponse;
import entity.User;
import service.LoginService;
import types.LoginStatus;
import view.LoginPage;
import view.LoginPage1;

import javax.swing.*;

public class LoginController {

    private static final LoginService loginService = new LoginService();
    private static User loggedInUser = null;

    public void login(LoginPage frame) {
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
        // 콘솔에 출력
        System.out.println("Username: " + email);
        System.out.println("Password: " + password);

        frame.dispose();
        new LoginPage1();
    }
}
