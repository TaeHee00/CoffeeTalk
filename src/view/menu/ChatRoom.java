package view.menu;

import controller.LoginController;
import entity.User;

import javax.swing.*;
import java.awt.*;

public class ChatRoom extends JPanel {

    static final JLabel label = new JLabel("채팅");
    static JButton searchButton;
    static JButton addChatButton;
    static int CHAT_ROOM_SIZE = 30;

    static User session;

    public ChatRoom() {
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

        // =============================== Add Chat Button ==============================
        ImageIcon addChatIcon = new ImageIcon(
                new ImageIcon("src/addChat.png").getImage()
                        .getScaledInstance(31, 31, java.awt.Image.SCALE_SMOOTH));
        addChatButton = new JButton(addChatIcon);
        addChatButton.setBounds(341, 15, 31, 31);

        // TODO: ActionListener -> Controller 패키지로 분리
        addChatButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "채팅방 추가 버튼이 클릭되었습니다."));

        addChatButton.setFocusPainted(false);
        addChatButton.setBorderPainted(false);
        addChatButton.setContentAreaFilled(false);

        add(addChatButton);
        // =============================== Add Chat Button ==============================


        // =============================== Search Button ==============================
        ImageIcon searchIcon = new ImageIcon(
                new ImageIcon("src/search.png").getImage()
                        .getScaledInstance(31, 31, java.awt.Image.SCALE_SMOOTH));
        searchButton = new JButton(searchIcon);
        searchButton.setBounds(298, 15, 31, 31);

        // TODO: ActionListener -> Controller 패키지로 분리
        searchButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "검색 버튼이 클릭되었습니다."));

        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);

        add(searchButton);
        // =============================== Search Button ==============================


        // ================================ Chat Room List ===============================
        JPanel chatListPanel = new JPanel();
        chatListPanel.setLayout(new BoxLayout(chatListPanel, BoxLayout.Y_AXIS));
        chatListPanel.setOpaque(false); // 배경을 투명하게 설정

        for (int i = 1; i <= CHAT_ROOM_SIZE; i++) {
            JButton chatRoom = createChatRoomButton("채팅방 " + (i));
            chatRoom.setMaximumSize(new Dimension(390, Integer.MAX_VALUE));
            chatRoom.setPreferredSize(new Dimension(390, chatRoom.getPreferredSize().height));
            chatListPanel.add(chatRoom);
        }

        JScrollPane scrollPane = new JScrollPane(chatListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBounds(0, 61, 390, 682);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane);
        // ================================ Chat Room List ===============================
    }

    private static JButton createChatRoomButton(String chatRoomName) {
        ImageIcon chatRoomIcon = new ImageIcon(
                new ImageIcon("src/userProfile.png").getImage()
                        .getScaledInstance(64, 64, Image.SCALE_SMOOTH));

        JButton chatRoomButton = new JButton();
        chatRoomButton.setFocusPainted(false);
        chatRoomButton.setBorderPainted(false);
        chatRoomButton.setContentAreaFilled(false);
        chatRoomButton.setText(chatRoomName);
        chatRoomButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        chatRoomButton.setIconTextGap(15);
        chatRoomButton.setMargin(new Insets(0, 8, 0, 0));
        chatRoomButton.setHorizontalAlignment(SwingConstants.LEFT);
        chatRoomButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        chatRoomButton.setVerticalTextPosition(SwingConstants.CENTER);
        chatRoomButton.setIcon(chatRoomIcon);
        chatRoomButton.setMinimumSize(new Dimension(390, chatRoomButton.getPreferredSize().height));
        chatRoomButton.setOpaque(false);
        return chatRoomButton;
    }
}
