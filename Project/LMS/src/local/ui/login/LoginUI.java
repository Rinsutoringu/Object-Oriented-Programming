package local.ui.login;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import standard.StandardUI;
import local.utils.*;

public class LoginUI extends StandardUI{

    errorHandler eh;

    public LoginUI() {
        eh = errorHandler.getInstance();
        
        // 布局定义为GBC，其他的具体配置已经在接口中实现了
        
        try {
            // 初始化面板

            createRegisterbox();

            init_index();
            init_pic();

            // 放置index面板进去
            utils.addComponent(this, panels.get("index"), gbc, 0, 0, 1, 1,
            GridBagConstraints.BOTH, 1, 1);

            gbc.insets = new Insets(10, 10, 10, 10);

            // 放置没有卵用的右侧展示板
            utils.addComponent(this, panels.get("pic"), gbc, 1, 0, 20, 1,
            GridBagConstraints.BOTH, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    // 关键信息面板
    private void init_index() throws Exception {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 100, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        int Cheight = 0;
        // utils.addComponent(panel, panel, gbc, ERROR, ALLBITS, HEIGHT, HEIGHT, ABORT, WIDTH, HEIGHT);

        // logo
        utils.addComponent(panel, getLogo(), gbc, 0, Cheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(210, 5, 5, 5);


        // 登陆组件
        utils.addComponent(panel, loginbox(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 5, 5, 5);

        // 登录按钮
        buttons.put("login", loginbutton());
        utils.addComponent(panel, buttons.get("login"), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        // 用户协议
        utils.addComponent(panel, usrprotocal(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        // 注册组件
        utils.addComponent(panel, getPanel("register"), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        panels.put("index", panel);
        return;
    }

    // 推荐信息面板
    private void init_pic() throws Exception {

        JPanel panel =  new JPanel() {
            private Image bgIMG = ImageUtils.loadImage("src\\resources\\loginBG.png");
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
        panels.put("pic", panel);
        return;
    }

    private JButton loginbutton() {
        JButton button = new JButton("Login");
        button.setPreferredSize(new Dimension(260, 60));
        return button;
    }

    private void createRegisterbox() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,5);

        buttons.put("regusr", new JButton("Register"));
        buttons.put("setdb", new JButton("Set DB Connect"));

        utils.addComponent(panel, getButton("regusr"), gbc, gleft, 0);

        utils.addComponent(panel, getButton("setdb"), gbc, gright, 0);

        // return panel;
        panels.put("register", panel);
    }
    

    // 登录组件
    private JPanel loginbox() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,5);

        short gheight = 0;

        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);

        utils.addComponent(panel, new JLabel("Login"), gbc, 0, ++gheight, 1, 1,
        GridBagConstraints.NONE, 2, 1);
        utils.addComponent(panel, new JLabel("User Name"), gbc, 0, ++gheight);
        textFields.put("loginusr", new JTextField(16));
        utils.addComponent(panel, textFields.get("loginusr"), gbc, 1, gheight);

        utils.addComponent(panel, new JLabel("PassWord"), gbc, 0, ++gheight);
        textFields.put("loginpwd", new JPasswordField(16));
        utils.addComponent(panel, textFields.get("loginpwd"), gbc, 1, gheight);
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
        Image img = ImageUtils.loadImage("src\\resources\\LMSlogo.png");
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
        if (textFields.containsKey(fieldName)) return textFields.get(fieldName);
        return null;
    }

    @Override
    public JPanel getThis() {
        return this;
    }
}

