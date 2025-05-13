package local.ui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import local.ui.StandardUI;
import local.utils.*;

public class LoginUI extends StandardUI{

    public LoginUI() {
        
        // 布局定义为GBC，其他的具体配置已经在接口中实现了
        
        panels.put("index", indexPanel());
        panels.put("pic", picJPanel());
        
        // 放置index面板进去
        utils.addComponent(this, panels.get("index"), gbc, 0, 0, 1, 1,
        GridBagConstraints.BOTH, 1, 1);

        gbc.insets = new Insets(10, 10, 10, 10);

        // 放置没有卵用的右侧展示板
        utils.addComponent(this, panels.get("pic"), gbc, 1, 0, 20, 1,
        GridBagConstraints.BOTH, 1, 1);
    }

    // 关键信息面板
    private JPanel indexPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
    /*######设置布局######*/
        gbc.insets = new Insets(5, 5, 100, 5);
    /*######设置布局######*/
        gbc.anchor = GridBagConstraints.CENTER;

        int Cheight = 0;
        // utils.addComponent(panel, panel, gbc, ERROR, ALLBITS, HEIGHT, HEIGHT, ABORT, WIDTH, HEIGHT);

        // logo
        utils.addComponent(panel, getLogo(), gbc, 0, Cheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

    /*######设置布局######*/
        gbc.insets = new Insets(220, 5, 5, 5);
    /*######设置布局######*/

        // 登陆组件
        utils.addComponent(panel, loginbox(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

    /*######设置布局######*/
        gbc.insets = new Insets(5, 5, 5, 5);
    /*######设置布局######*/

        // 登录按钮
        buttons.put("login", loginbutton());
        utils.addComponent(panel, buttons.get("login"), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        // 用户协议
        utils.addComponent(panel, usrprotocal(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        // 注册组件
        utils.addComponent(panel, registerbox(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        return panel;
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

    // 注册组件
    private JPanel registerbox() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,5);

        utils.addComponent(panel, new JLabel("Register"), gbc, 0, 0);
        utils.addComponent(panel, new JLabel("User Name"), gbc, 0, 1);
        inputBoxs.put("regusr", new JTextField(16));
        utils.addComponent(panel, inputBoxs.get("regusr"), gbc, 1, 1);

        utils.addComponent(panel, new JLabel("PassWord"), gbc, 0, 2);
        inputBoxs.put("regpwd", new JPasswordField(16));
        utils.addComponent(panel, inputBoxs.get("regpwd"), gbc, 1, 2);
        buttons.put("reg", new JButton("reg"));
        utils.addComponent(panel, buttons.get("reg"), gbc, 3, 1, 1, 1,
            GridBagConstraints.NONE, 1, 2);
        // DEBUG
        // panel.setBackground(Color.red);
        return panel;
    }

    // 登录组件
    private JPanel loginbox() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,5);

        utils.addComponent(panel, new JLabel("Register"), gbc, 0, 0);
        utils.addComponent(panel, new JLabel("User Name"), gbc, 0, 1);
        inputBoxs.put("loginusr", new JTextField(16));
        utils.addComponent(panel, inputBoxs.get("loginusr"), gbc, 1, 1);

        utils.addComponent(panel, new JLabel("PassWord"), gbc, 0, 2);
        inputBoxs.put("loginpwd", new JPasswordField(16));
        utils.addComponent(panel, inputBoxs.get("loginpwd"), gbc, 1, 2);
        // DEBUG
        // panel.setBackground(Color.red);
        return panel;
    }

    // 用户协议
    private JPanel usrprotocal() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panel.setBackground(new Color(255, 255, 255));
        JLabel label = new JLabel("Check to agree to the \"User Agreement\".");
        this.checkBoxs.put("check", new JCheckBox());
        panel.add(checkBoxs.get("check"));
        panel.add(label);
        return panel;
    }

    // logo
    private JLabel getLogo() {
        Image img = ImageUtils.loadImage("src\\resoueces\\LMSlogo.png");
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(img);
        return ImageUtils.imgToJLable(bufferedImage, 150, 150, 10);
    } 

    // 获取按钮对象
    @Override
    public AbstractButton getButton(String buttonName) {
        if (buttons.containsKey(buttonName)) return (JButton) buttons.get(buttonName);
        return null;
    }

    // 获取输入框对象
    @Override
    public JTextField getTextField(String fieldName) {
        if (inputBoxs.containsKey(fieldName)) return inputBoxs.get(fieldName);
        return null;
    }

    @Override
    public JPanel getThis() {
        return this;
    }
}

