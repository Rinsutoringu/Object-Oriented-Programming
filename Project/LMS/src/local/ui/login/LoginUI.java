package local.ui.login;


import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


public class LoginUI extends JPanel{

    local.localUtils utils = new local.localUtils();

    public LoginUI() {

        // 创建GB模式布局
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // 设置组件间隔
        // gbc你有病吧
        gbc.insets = new Insets(10, 10, 10, 10);

        // 放置index面板进去
        utils.addComponent(this, indexPanel(), gbc, 0, 0, 1, 1,1, 1, GridBagConstraints.NONE);

        // 放置没有卵用的右侧展示板
        utils.addComponent(this, picJPanel(), gbc, 1, 0, 6, 1, 1, 4, GridBagConstraints.NONE);

    }



    
    // 关键信息面板
    private JPanel indexPanel() {
        JPanel indexpanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        utils.addComponent(indexpanel,usernamebox() ,gbc, 0, 0);

        utils.addComponent(indexpanel, pwbox(), gbc, 0, 1);
        
        utils.addComponent(indexpanel, usrprotocal(), gbc, 0, 2);
        
        utils.addComponent(indexpanel, loginbutton(), gbc, 0, 3);

        indexpanel.setBackground(Color.BLACK);
        return indexpanel;
    }

    // 推荐信息面板
    private JPanel picJPanel() {
        JPanel picjpanel = new JPanel();
        picjpanel.setBackground(Color.CYAN);
        return picjpanel;
    }



    private JButton loginbutton() {
        JButton button = new JButton("Login");
        return button;
    }
    private JButton helpButton() {
        JButton button = new JButton("help");
        return button;
    }

    // 用户名输入框
    private JPanel usernamebox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.add(new JLabel("User Name: "));
        panel.add(new JTextField(16));
        return panel;
    }

    // 密码输入框
    private JPanel pwbox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.add(new JLabel("Password: "));
        panel.add(new JPasswordField(16));
        return panel;
    }

    // 用户协议
    private JPanel usrprotocal() {
        JPanel usrp = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JLabel proto = new JLabel("Check to agree to the \"User Agreement\".");
        JCheckBox check = new JCheckBox();
        usrp.add(check);
        usrp.add(proto);
        return usrp;
    }
}
