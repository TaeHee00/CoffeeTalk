package view;

import controller.LoginController;
import controller.SignupController;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    public Container container;
    public JTextField userText;
    public JPasswordField passwordText;
    public JButton loginButton;
    public JButton signupButton;
    public JButton findPwButton;
    public JLabel idLabel;
    public JLabel passwordLabel;
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
        container.add(label);

        idLabel = new JLabel("이메일");
        idLabel.setBounds(36, 310, 131, 27);
        container.add(idLabel);

        userText = new JTextField();
        userText.setBounds(36, 342, 319, 48);
        container.add(userText);

        passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(36, 395, 131, 27);
        container.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(36, 422, 319, 48);
        container.add(passwordText);

        // 로그인 버튼
        loginButton = new JButton("로그인");
        loginButton.setBounds(36, 483, 319, 47);
        loginButton.setBackground(new Color(0x886F65));
        loginButton.addActionListener(e -> loginController.login(this));
        container.add(loginButton);

        // 회원가입 버튼
        signupButton = new JButton("회원가입");
        signupButton.setBounds(36, 530, 319, 47);
        signupButton.addActionListener(e -> loginController.signupPage(this));
        container.add(signupButton);

        // 비밀번호 찾기 버튼
        findPwButton = new JButton("비밀번호를 잊어버리셨나요?");
        findPwButton.setBounds(97, 711, 197, 39);
        container.add(findPwButton);


        setVisible(true);
    }
}
