package local.ui.login;


import javax.swing.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
public class LoginUI extends JPanel{
    
    public LoginUI() {

        // 创建GB模式布局
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // 设置组件间隔
        // gbc你有病吧
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // 放置index面板进去
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(indexPanel(), gbc);
    }


    private JPanel indexPanel() {
        JPanel indexpanel = new JPanel();
        indexpanel.add(loginbutton());
        indexpanel.add(helpButton());
        indexpanel.add(usernamebox());
        indexpanel.add(pwbox());
        indexpanel.setBackground(Color.BLACK);
        return indexpanel;
    }
    private JButton loginbutton() {
        JButton button = new JButton("Login");
        return button;
    }
    private JButton helpButton() {
        JButton button = new JButton("help");
        return button;
    }
    private JTextField usernamebox() {
        JTextField username = new JTextField(16);
        return username;
    }
    private JPasswordField pwbox() {
        JPasswordField pw = new JPasswordField(16);
        return pw;
    }
}
