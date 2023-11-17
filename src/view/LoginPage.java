package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    public Container container;
    public JTextField userText;
    public JPasswordField passwordText;
    public JButton loginButton;
    public JButton sigupButton;
    public JButton findPwButton;
    private static final LoginController loginController = new LoginController();

    public LoginPage() {
        // 프레임 설정
        setTitle("Login Page");
        setBounds(0, 0, 390, 844);
        getContentPane().setBackground(new Color(0xF5F5F5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // 컨테이너 설정
        container = getContentPane();
        container.setLayout(null);

        // 이미지 아이콘 생성
        ImageIcon icon = new ImageIcon("src/logo.png");

        // JLabel에 이미지 아이콘 설정
        JLabel label = new JLabel(icon);
        label.setBounds(94, 122, 227, 173);

        // JLabel을 JFrame에 추가
        add(label);

        // 사용자 이름 텍스트 필드
        userText = new JTextField("아이디 (이메일)");
        userText.setBounds(36, 368, 319, 53);
        container.add(userText);

        // 비밀번호 텍스트 필드
        passwordText = new JPasswordField("비밀번호");
        passwordText.setBounds(36, 412, 319, 53);
        container.add(passwordText);

        // 로그인 버튼
        loginButton = new JButton("로그인");
        loginButton.setBounds(36, 483, 319, 47);
        loginButton.addActionListener(e -> loginController.login(this));
        container.add(loginButton);

        // 회원가입 버튼
        sigupButton = new JButton("회원가입");
        sigupButton.setBounds(36, 530, 319, 47);
        container.add(sigupButton);

        // 비밀번호 찾기 버튼
        findPwButton = new JButton("비밀번호를 잊어버리셨나요?");
        findPwButton.setBounds(97, 711, 197, 39);
        container.add(findPwButton);

        setVisible(true);
    }
}
