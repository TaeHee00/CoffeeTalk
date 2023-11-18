package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class SignupPage extends JFrame {
    public Container container;
    public JLabel idLabel;
    public JTextField idText;
    public JLabel passwordLabel;
    public JPasswordField passwordText;
    public JLabel passwordConfirmLabel;
    public JPasswordField passwordConfirmText;
    public JLabel nameLabel;
    public JTextField nameText;
    public JLabel nicknameLabel;
    public JTextField nicknameText;

    public JLabel birthdayLabel;
    public JComboBox yearText;
    public JComboBox monthText;
    public JComboBox dayText;
    public JButton signupButton;
    private static final LoginController loginController = new LoginController();

    public SignupPage() {
        // 프레임 설정
        setTitle("Signup Page");
        setBounds(0, 0, 390, 844);
        getContentPane().setBackground(new Color(0xF5F5F5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // 컨테이너 설정
        container = getContentPane();
        container.setLayout(null);

        // 이미지 아이콘 생성
        ImageIcon icon = new ImageIcon("src/signupImage.png");

        // JLabel에 이미지 아이콘 설정
        JLabel label = new JLabel(icon);
        label.setBounds(127, 27, 150, 114);

        // JLabel을 JFrame에 추가
        container.add(label);


        idLabel = new JLabel("이메일");
        idLabel.setBounds(36, 170, 131, 27);
        container.add(idLabel);

        idText = new JTextField();
        idText.setBounds(36, 202, 319, 48);
        container.add(idText);

        passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(36, 255, 131, 27);
        container.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(36, 282, 319, 48);
        container.add(passwordText);

        passwordConfirmLabel = new JLabel("비밀번호 확인");
        passwordConfirmLabel.setBounds(36, 335, 131, 27);
        container.add(passwordConfirmLabel);

        passwordConfirmText = new JPasswordField();
        passwordConfirmText.setBounds(36, 362, 319, 48);
        container.add(passwordConfirmText);

        nameLabel = new JLabel("이름");
        nameLabel.setBounds(36, 415, 131, 27);
        container.add(nameLabel);

        nameText = new JTextField();
        nameText.setBounds(36, 442, 319, 48);
        container.add(nameText);

        nicknameLabel = new JLabel("닉네임");
        nicknameLabel.setBounds(36, 505, 131, 27);
        container.add(nicknameLabel);

        nicknameText = new JTextField();
        nicknameText.setBounds(36, 532, 319, 48);
        container.add(nicknameText);

        birthdayLabel = new JLabel("생년월일");
        birthdayLabel.setBounds(36, 585, 131, 27);
        container.add(birthdayLabel);

        int cnt = 0;
        int startYear = 1900;
        int endYear = LocalDateTime.now().getYear();
        String[] yearList = new String[endYear - startYear + 1];
        for (int i = endYear; i >= startYear; i--) {
            yearList[cnt++] = String.valueOf(i);
        }
        yearText = new JComboBox(yearList);
        yearText.setBounds(36, 612, 131, 48);
        container.add(yearText);

        cnt = 0;
        int startMonth = 1;
        int endMonth = 12;
        String[] monthList = new String[endMonth - startMonth + 1];
        for (int i = startMonth; i <= endMonth; i++) {
            monthList[cnt++] = String.valueOf(i);
        }
        monthText = new JComboBox(monthList);
        monthText.setBounds(173, 612, 88, 48);
        container.add(monthText);

        cnt = 0;
        int startDay = 1;
        int endDay = 31;
        String[] dayList = new String[endDay - startDay + 1];
        for (int i = startDay; i <= endDay; i++) {
            dayList[cnt++] = String.valueOf(i);
        }
        dayText = new JComboBox(dayList);
        dayText.setBounds(267, 612, 88, 48);
        container.add(dayText);

        signupButton = new JButton("회원가입");
        signupButton.setBounds(36, 723, 319, 47);
        signupButton.setBackground(new Color(0x886F65));
//        sigupButton.addActionListener(e -> loginController.signup(this));
        container.add(signupButton);

        setVisible(true);
    }
}
