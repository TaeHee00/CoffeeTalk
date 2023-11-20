package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomNavigationBar extends JPanel {
    private static final int ROWS = 1;
    private static final int COLS = 4;
    private static CardLayout layout;
    private static JPanel currentMenu;

    public BottomNavigationBar(JPanel currentMenu, CardLayout layout) {
        super(new GridLayout(ROWS, COLS));

        setPreferredSize(new Dimension(390, 80));
        Border topLine = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
        Border topPadding = BorderFactory.createEmptyBorder(5, 0, 5, 0);
        Border compoundBorder = new CompoundBorder(topLine, topPadding);
        setBorder(compoundBorder);

        BottomNavigationBar.layout = layout;
        BottomNavigationBar.currentMenu = currentMenu;
        JButton friendListButton = createNavBarButton("친구 목록");
        JButton chatRoomButton = createNavBarButton("채팅방");
        JButton randomChatButton = createNavBarButton("무작위 채팅");
        JButton settingButton = createNavBarButton("설정");

        add(friendListButton);
        add(chatRoomButton);
        add(randomChatButton);
        add(settingButton);

        friendListButton.addActionListener(new NavButtonListener("친구 목록"));
        chatRoomButton.addActionListener(new NavButtonListener("채팅방"));
        randomChatButton.addActionListener(new NavButtonListener("무작위 채팅"));
        settingButton.addActionListener(new NavButtonListener("설정"));
    }

    private JButton createNavBarButton(String text) {
        JButton button = new JButton();
        try {
            String imgName = "";
            switch (text) {
                case "친구 목록" -> imgName = "src/friendList.png";
                case "채팅방" -> imgName = "src/chatRoom.png";
                case "무작위 채팅" -> imgName = "src/randomChat.png";
                case "설정" -> imgName = "src/setting.png";
            }
            button.setIcon(new ImageIcon(imgName));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private class NavButtonListener implements ActionListener {
        private final String panelName;

        public NavButtonListener(String panelName) {
            this.panelName = panelName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            layout.show(currentMenu, panelName);
        }
    }
}
