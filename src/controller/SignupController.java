package controller;

import dto.LoginRequest;
import dto.LoginResponse;
import dto.SignupRequest;
import dto.SignupResponse;
import entity.User;
import service.LoginService;
import service.SignupService;
import types.LoginStatus;
import types.SignupStatus;
import view.LoginPage;
import view.MenuPage;
import view.SignupPage;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Optional;

public class SignupController {

    private static final SignupService signupService = new SignupService();

    public void signup(SignupPage frame) {
        if (!validate(frame)) return;

        String email = frame.idText.getText();
        char[] passwordChars = frame.passwordText.getPassword();
        String password = new String(passwordChars);
        char[] passwordConfirmChars = frame.passwordConfirmText.getPassword();
        String passwordConfirm = new String(passwordConfirmChars);
        String name = frame.nameText.getText();
        String nickname = frame.nicknameText.getText();
        int year = Integer.parseInt(frame.yearText.getSelectedItem().toString()) - 1900;
        int month = Integer.parseInt(frame.monthText.getSelectedItem().toString()) - 1;
        int day = Integer.parseInt(frame.dayText.getSelectedItem().toString());
        Timestamp birthday = new Timestamp(year, month, day, 0, 0, 0, 0);

        SignupRequest request = SignupRequest.builder()
                .email(email)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .name(name)
                .nickname(nickname)
                .birthday(birthday)
                .build();

        SignupResponse response = signupService.signup(request);

        switch (response.signupStatus()) {
            case EMAIL_ALREADY_EXISTS -> JOptionPane.showMessageDialog(null, "이미 사용중인 이메일 입니다.");
            case PASSWORD_CONFIRMATION_MISMATCH -> JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            case SIGNUP_SUCCESS -> JOptionPane.showMessageDialog(null, "회원가입 성공!");
        }

        // 회원가입 성공이 아니면 탈출
        if (response.signupStatus() != SignupStatus.SIGNUP_SUCCESS) return;

        LoginController.setLoggedInUser(response.user());

        frame.dispose();
        new MenuPage();
    }

    private boolean validate(SignupPage frame) {
        String email = frame.idText.getText();
        char[] passwordChars = frame.passwordText.getPassword();
        String password = new String(passwordChars);
        char[] passwordConfirmChars = frame.passwordConfirmText.getPassword();
        String passwordConfirm = new String(passwordConfirmChars);
        String name = frame.nameText.getText();
        String nickname = frame.nicknameText.getText();

        if (email.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
            return false;
        }
        if (password.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
            return false;
        }
        if (passwordConfirm.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해주세요.");
            return false;
        }
        if (name.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
            return false;
        }
        if (nickname.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요.");
            return false;
        }

        return true;
    }
}
