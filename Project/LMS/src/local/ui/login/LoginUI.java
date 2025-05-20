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
        
        try {
            createRegisterbox();

            init_index();
            init_pic();
            setFontSize();
            setStyle();

            utils.addComponent(this, panels.get("index"), gbc, 0, 0, 1, 1,
            GridBagConstraints.BOTH, 1, 1);

            gbc.insets = new Insets(10, 10, 10, 10);

            utils.addComponent(this, panels.get("pic"), gbc, 1, 0, 20, 1,
            GridBagConstraints.BOTH, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }
    @Override
    protected void setStyle() {
        getTextField("loginusr").setPreferredSize(new Dimension(200, 30));
        getTextField("loginpwd").setPreferredSize(new Dimension(200, 30));
    }

    private void setFontSize() {
        setFontSize(getButton("login"), 18);
    }

    private void init_index() throws Exception {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 100, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        int Cheight = 0;

        utils.addComponent(panel, getLogo(), gbc, 0, Cheight, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(210, 5, 5, 5);

        utils.addComponent(panel, loginbox(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        gbc.insets = new Insets(5, 5, 5, 5);

        utils.addComponent(panel, usrprotocal(), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        buttons.put("login", loginbutton());
        utils.addComponent(panel, buttons.get("login"), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 2, 1);

        utils.addComponent(panel, getPanel("register"), gbc, 0, Cheight++, 1, 1,
            GridBagConstraints.NONE, 1, 1);

        panels.put("index", panel);
        return;
    }

    private void init_pic() throws Exception {

        JPanel panel =  new JPanel() {
            private Image bgIMG = ImageUtils.loadImage("/resources/loginBG.png");
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
        buttons.put("setdb", new JButton(" Set DB "));

        utils.addComponent(panel, getButton("regusr"), gbc, gleft, 0);

        utils.addComponent(panel, getButton("setdb"), gbc, gright, 0);

        panels.put("register", panel);
    }

    private JPanel loginbox() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,5);

        putLabel("username", new JLabel("UserName"));
        putLabel("password", new JLabel("Password"));

        short gheight = 0;

        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);
        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);

        utils.addComponent(panel, new JLabel(" "), gbc, 0, ++gheight);

        utils.addComponent(panel, getLabel("username"), gbc, 0, ++gheight);
        textFields.put("loginusr", new JTextField(22));
        utils.addComponent(panel, textFields.get("loginusr"), gbc, 1, gheight);

        utils.addComponent(panel, getLabel("password"), gbc, 0, ++gheight);
        textFields.put("loginpwd", new JPasswordField(16));
        utils.addComponent(panel, textFields.get("loginpwd"), gbc, 1, gheight);
        return panel;
    }

    private JPanel usrprotocal() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        panel.setBackground(new Color(255, 255, 255));
        JLabel label = new JLabel(" Agree the \"User Agreement\". ");
        this.checkBoxs.put("check", new JCheckBox());
        panel.add(checkBoxs.get("check"));
        panel.add(label);
        return panel;
    }

    private JLabel getLogo() {
        Image img = ImageUtils.loadImage("/resources/LMSlogo.png");
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(img);
        return ImageUtils.imgToJLable(bufferedImage, 150, 150, 10);
    } 

    @Override
    public AbstractButton getButton(String buttonName) {
        if (buttons.containsKey(buttonName)) return (JButton) buttons.get(buttonName);
        return null;
    }

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

