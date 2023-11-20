package view;

import view.menu.ChatRoom;
import view.menu.FriendList;
import view.menu.RandomChat;
import view.menu.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class MenuPage extends JFrame {
    private JPanel currentMenu;
    private static CardLayout layout;

    public MenuPage() {
        setTitle("CoffeeTalk");
        setBounds(0, 0, 390, 844);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        layout = new CardLayout();
        currentMenu = new JPanel(layout);
        ImagePanel background = new ImagePanel();
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        FriendList friendList = new FriendList();
        friendList.setPreferredSize(new Dimension(390, 764));
        friendList.setOpaque(false);
        currentMenu.add(friendList, "친구 목록");

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPreferredSize(new Dimension(390, 764));
        chatRoom.setOpaque(false);
        currentMenu.add(chatRoom, "채팅방");

        RandomChat randomChat = new RandomChat();
        randomChat.setPreferredSize(new Dimension(390, 764));
        randomChat.setOpaque(false);
        currentMenu.add(randomChat, "무작위 채팅");

        Setting setting = new Setting();
        setting.setPreferredSize(new Dimension(390, 764));
        setting.setOpaque(false);
        currentMenu.add(setting, "설정");

        currentMenu.setOpaque(false);
        layout.show(currentMenu, "친구 목록");

        JPanel bottomNavBar = new BottomNavigationBar(currentMenu, layout);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1; // 수평 크기를 늘리지 않음
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH; // 수평 및 수직으로 확장
        background.add(currentMenu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1; // 수평 크기를 늘리지 않음
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 수평으로만 확장
        background.add(bottomNavBar, gbc);

        add(background);
        setVisible(true);
    }

    private static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel() {
            backgroundImage = new ImageIcon("src/background.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), (ImageObserver) this);
        }
    }
}
