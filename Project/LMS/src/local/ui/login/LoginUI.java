package local.ui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import local.utils.*;


public class LoginUI extends JPanel{

    private local.utils.UIUtils utils = new local.utils.UIUtils();

    public LoginUI() {

        // 初始化GBC对象并设置间隔
        // gbc你有病吧
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // 放置index面板进去
        utils.addComponent(this, indexPanel(), gbc, 0, 0, 1, 1,
            1, 1, GridBagConstraints.NONE);

        gbc.insets = new Insets(10, 10, 10, 10);

        // 放置没有卵用的右侧展示板
        utils.addComponent(this, picJPanel(), gbc, 1, 0, 20, 1, 
            1, 1, GridBagConstraints.NONE);
    }

    // 关键信息面板
    private JPanel indexPanel() {
        JPanel indexpanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 5, 180, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        // logo 
        utils.addComponent(indexpanel, getLogo(), gbc, 0,0);


        gbc.insets = new Insets(5, 5, 5, 5);

        // 用户名&密码
        utils.addComponent(indexpanel, loginbox(), gbc, 0, 1);
        // // 用户名
        // utils.addComponent(indexpanel,usernamebox() ,gbc, 0, 1);
        // // 密码
        // utils.addComponent(indexpanel, pwbox(), gbc, 0, 2);
        // 用户协议
        utils.addComponent(indexpanel, usrprotocal(), gbc, 0, 3);
        // 登录按钮
        utils.addComponent(indexpanel, loginbutton(), gbc, 0, 4);

        // indexpanel.setBackground(Color.BLACK);
        return indexpanel;
    }

    // 推荐信息面板
    private JPanel picJPanel() {

        return new JPanel() {
            private Image bgIMG = ImageUtils.loadImage("src\\resoueces\\loginBG.png");
            public BufferedImage bfIMG = ImageUtils.toBufferedImage(bgIMG);
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgIMG != null) {
                    Point point = ImageUtils.zoom(ImageUtils.getSizeBfIMG(bfIMG), new Point(getWidth(), getHeight()));
                    g.drawImage(bgIMG, 0, 0, (int) point.getX(), (int) point.getY(), this);
                }
            }
        };
    }



    private JButton loginbutton() {
        JButton button = new JButton("Login");
        button.setPreferredSize(new Dimension(260, 60));
        return button;
    }
    private JButton helpButton() {
        JButton button = new JButton("help");
        return button;
    }

    // 用户名输入框
    private JPanel usernamebox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panel.add(new JLabel("User Name: "));
        panel.add(new JTextField(16));
        return panel;
    }

    // 密码输入框
    private JPanel pwbox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panel.add(new JLabel("Password: "));
        panel.add(new JPasswordField(16));
        return panel;
    }

    private JPanel loginbox() {
        JPanel loginbox = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,5);
        // loginbox.setBackground(Color.DARK_GRAY);
        // loginbox.setPreferredSize(new Dimension(2000, 2000));
        utils.addComponent(loginbox, new JLabel("User Name"), gbc, 0, 0);
        utils.addComponent(loginbox, new JTextField(16), gbc, 1, 0);

        utils.addComponent(loginbox, new JLabel("PassWord"), gbc, 0, 1);
        utils.addComponent(loginbox, new JTextField(16), gbc, 1, 1);
        return loginbox;
    }

    // 用户协议
    private JPanel usrprotocal() {
        JPanel usrp = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        JLabel proto = new JLabel("Check to agree to the \"User Agreement\".");
        JCheckBox check = new JCheckBox();
        usrp.add(check);
        usrp.add(proto);
        return usrp;
    }

    // logo
    private JLabel getLogo() {
        Image img = ImageUtils.loadImage("src\\resoueces\\images.png");
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(img);
        return ImageUtils.imgToJLable(bufferedImage, 120, 120, 10);
    } 
}

