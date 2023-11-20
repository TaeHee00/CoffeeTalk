package view.menu;

import controller.LoginController;
import entity.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FriendList extends JPanel {

    static final JLabel label = new JLabel("친구");
    static JButton searchButton;
    static JButton myProfile;
    static JLabel friendCountLabel;

    static int FRIEND_SIZE = 30;

    static User session;

    public FriendList() {
        // 로그인 하지 않고 넘어왔을 경우 강제 종료
         if (LoginController.getLoggedInUser().isEmpty()) {
             JOptionPane.showInternalMessageDialog(this, "로그인이 필요합니다.");
             System.exit(-1);
         } else {
             session = LoginController.getLoggedInUser().get();
         }


        setLayout(null);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 24));
        label.setBounds(23, 17, 51, 27);
        add(label);

        // =============================== Search Button ==============================
        ImageIcon searchIcon = new ImageIcon(
                new ImageIcon("src/search.png").getImage()
                        .getScaledInstance(31, 31, java.awt.Image.SCALE_SMOOTH));
        searchButton = new JButton(searchIcon);
        searchButton.setBounds(343, 15, 31, 31);

        // TODO: ActionListener -> Controller 패키지로 분리
        searchButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "검색 버튼이 클릭되었습니다."));

        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);

        add(searchButton);
        // =============================== Search Button ==============================

        // ================================ My Profile ===============================
        ImageIcon userIcon = new ImageIcon(
                new ImageIcon("src/userProfile.png").getImage()
                        .getScaledInstance(75, 75, Image.SCALE_SMOOTH));
        myProfile = new JButton();
        myProfile.setFocusPainted(false);
        myProfile.setBorderPainted(false);
        myProfile.setContentAreaFilled(false);
        myProfile.setText(session.nickname());
        myProfile.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
        myProfile.setIconTextGap(15);
        myProfile.setMargin(new Insets(0, 8, 0, 0));
        myProfile.setHorizontalAlignment(SwingConstants.LEFT);
        myProfile.setHorizontalTextPosition(SwingConstants.RIGHT);
        myProfile.setVerticalTextPosition(SwingConstants.CENTER);
        myProfile.setIcon(userIcon);
        myProfile.setBounds(0, 61, 390, 90);
        add(myProfile);
        // ================================ My Profile ===============================

        // ================================ Friend List Label ===============================
        friendCountLabel = new JLabel("     친구 " + FRIEND_SIZE);
        friendCountLabel.setFont(new Font(label.getFont().getName(), Font.PLAIN, 16));
        Border matteBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
        Border emptyBorder = new EmptyBorder(20, 0, 10, 0);
        CompoundBorder compoundBorder = new CompoundBorder(matteBorder, emptyBorder);
        friendCountLabel.setBorder(compoundBorder);
        friendCountLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        friendCountLabel.setBounds(0, 157, 390, 30);
        add(friendCountLabel);
        // ================================ Friend List Label ===============================

        // ================================ Friend List ===============================
        JPanel friendListPanel = new JPanel();
        friendListPanel.setLayout(new BoxLayout(friendListPanel, BoxLayout.Y_AXIS));
        friendListPanel.setOpaque(false); // 배경을 투명하게 설정

        for (int i = 1; i <= FRIEND_SIZE; i++) {
            JButton friendProfile = createFriendProfileButton("친구 " + (i));
            friendProfile.setMaximumSize(new Dimension(390, Integer.MAX_VALUE));
            friendProfile.setPreferredSize(new Dimension(390, friendProfile.getPreferredSize().height));
            friendListPanel.add(friendProfile);
        }

        JScrollPane scrollPane = new JScrollPane(friendListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBounds(0, 187, 390, 577);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane);
        // ================================ Friend List ===============================
    }

    private static JButton createFriendProfileButton(String friendName) {
        ImageIcon friendIcon = new ImageIcon(
                new ImageIcon("src/userProfile.png").getImage()
                        .getScaledInstance(64, 64, Image.SCALE_SMOOTH));

        JButton friendProfile = new JButton();
        friendProfile.setFocusPainted(false);
        friendProfile.setBorderPainted(false);
        friendProfile.setContentAreaFilled(false);
        friendProfile.setText(friendName);
        friendProfile.setFont(new Font("SansSerif", Font.PLAIN, 18));
        friendProfile.setIconTextGap(15);
        friendProfile.setMargin(new Insets(0, 8, 0, 0));
        friendProfile.setHorizontalAlignment(SwingConstants.LEFT);
        friendProfile.setHorizontalTextPosition(SwingConstants.RIGHT);
        friendProfile.setVerticalTextPosition(SwingConstants.CENTER);
        friendProfile.setIcon(friendIcon);
        friendProfile.setMinimumSize(new Dimension(390, friendProfile.getPreferredSize().height));
        friendProfile.setOpaque(false);
        return friendProfile;
    }
}
