package standard;

import javax.swing.*;

import local.error.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;

/**
 * 标准UI类
 * 该类是所有UI类的父类，提供了一些通用的方法和属性
 * 主要用于存储按钮、面板、输入框和图片等组件
 * 以及提供获取这些组件的方法
 */

public abstract class StandardUI extends JPanel {

    public static final short gleft = 0;
    public static final short gright = 1;
    public static final short gmiddle = 0;

    /**
     * 工具类
     */
    public static final local.utils.UIUtils utils = local.utils.UIUtils.getInstance();

    /**
     * 按钮集合
     */
    protected Map<String, AbstractButton> buttons = new LinkedHashMap<String, AbstractButton>();
    /**
     * 面板集合
     */
    protected Map<String, JPanel> panels = new LinkedHashMap<String, JPanel>();
    /**
     * 输入框集合 // TODO 我知道这个名字是有问题的但是我不是很想改
     */
    protected Map<String, JTextField> textFields = new LinkedHashMap<String, JTextField>();
    /**
     * 图片集合
     */
    protected Map<String, BufferedImage> images = new LinkedHashMap<String, BufferedImage>();
    /**
     * 选择框集合
     */
    protected Map<String, JCheckBox> checkBoxs = new LinkedHashMap<String, JCheckBox>();

    /**
     * 下拉框集合
     */
    protected Map<String, JComboBox<String>> comboBoxs = new LinkedHashMap<String, JComboBox<String>>();

    /**
     * 密码输入框集合
     */
    protected Map<String, JPasswordField> passwordFields = new LinkedHashMap<String, JPasswordField>();

    /**
     * Lable集合
     */
    protected Map<String, JLabel> labels = new LinkedHashMap<String, JLabel>();

    protected GridBagConstraints gbc;

    /**
     * 注册新的按钮
     * @param name 按钮名称
     * @param button 按钮对象
     */
    public void putButton(String name, AbstractButton button) {
        buttons.put(name, button);
    }

    /**
     * 注册新的面板
     * @param name 面板名称
     * @param panel 面板对象
     */
    public void putPanel(String name, JPanel panel) {
        panels.put(name, panel);
    }

    /**
     * 注册新的输入框
     * @param name 输入框名称
     * @param textField 输入框对象
     */
    public void putTextField(String name, JTextField textField) {
        textFields.put(name, textField);
    }

    /**
     * 注册新的图片
     * @param name 图片名称
     * @param image 图片对象
     */
    public void putImage(String name, BufferedImage image) {
        images.put(name, image);
    }

    /**
     * 注册新的选择框
     * @param name 选择框名称
     * @param checkBox 选择框对象
     */
    public void putCheckBox(String name, JCheckBox checkBox) {
        checkBoxs.put(name, checkBox);
    }

    /**
     * 注册新的下拉框
     * @param name 下拉框名称
     * @param comboBox 下拉框对象
     */
    public void putComboBox(String name, JComboBox<String> comboBox) {
        comboBoxs.put(name, comboBox);
    }

    /**
     * 注册新的密码输入框
     * @param name 密码输入框名称
     * @param passwordField 密码输入框对象
     */
    public void putPasswordField(String name, JPasswordField passwordField) {
        passwordFields.put(name, passwordField);
    }


    /**
     * 获取按钮
     * @param name 获取到按钮名称，以键的方式存储在buttons集合中
     * @return 返回按钮对象
     * @exception ButtonNotFound 没找到按钮
     */
    public AbstractButton getButton(String name) throws ButtonNotFound {
        AbstractButton button;
        try {
            button = buttons.get(name);
            // 如果按钮不为空，直接返回
            // 如果按钮为空，抛出异常
            if (button != null) return button;
            throw new NullPointerException("Button with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new ButtonNotFound("Fail to found button with key " + name, e);
        }
    }

    /**
     * 获取面板
     * @param name 获取到面板名称，以键的方式存储在panels集合中
     * @return 返回面板对象
     * @exception PanelNotFound 没找到面板
     */
    public JPanel getPanel(String name) throws PanelNotFound{
        JPanel panel;
        try {
            panel = panels.get(name);
            // 如果面板不为空，直接返回
            // 如果面板为空，抛出异常
            if (panel != null) return panel;
            throw new NullPointerException("Panel with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new PanelNotFound("Fail to found panel with key " + name, e);
        }
    }

    /**
     * 获取输入框
     * @param name 获取到输入框名称，以键的方式存储在inputBoxs集合中
     * @return 返回输入框对象
     */
    public JTextField getTextField(String name) throws InputNotFound{
        JTextField inputBox;
        try {
            inputBox = textFields.get(name);
            // 如果输入框不为空，直接返回
            // 如果输入框为空，抛出异常
            if (inputBox != null) return inputBox;
            throw new NullPointerException("Input box with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found input box with key " + name, e);
        }
    }

    /**
     * 获取图片
     * @param name 获取到图片名称，以键的方式存储在images集合中
     * @return 返回图片对象
     */
    public BufferedImage getImage(String name) throws ImageNotFound{
        BufferedImage image;
        try {
            image = images.get(name);
            // 如果图片不为空，直接返回
            // 如果图片为空，抛出异常
            if (image != null) return image;
            throw new NullPointerException("Image with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new ImageNotFound("Fail to found image with key " + name, e);
        }
    }

    /**
     * 获取选择框
     * @param name 获取到输入框名称，以键的方式存储在inputBoxs集合中
     * @return 返回选择框对象
     */
    public JCheckBox getCheckBox(String name) throws InputNotFound{
        JCheckBox checkBox;
        try {
            checkBox = checkBoxs.get(name);
            // 如果选择框不为空，直接返回
            // 如果选择框为空，抛出异常
            if (checkBox != null) return checkBox;
            throw new NullPointerException("Checkbox with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found checkbox with key " + name, e);
        }
    }

    /**
     * 获取下拉框
     * @param name 获取到下拉框名称，以键的方式存储在comboBoxs集合中
     * @return 返回下拉框对象
     */
    public JComboBox<String> getComboBox(String name) throws InputNotFound{
        JComboBox<String> comboBox;
        try {
            comboBox = comboBoxs.get(name);
            // 如果下拉框不为空，直接返回
            // 如果下拉框为空，抛出异常
            if (comboBox != null) return comboBox;
            throw new NullPointerException("Combo box with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found combobox with key " + name, e);
        }
    }

    /**
     * 获取密码输入框
     * @param name 获取到密码输入框名称，以键的方式存储在passwordFields集合中
     * @return 返回密码输入框对象
     */
    public JPasswordField getPasswordField(String name) throws InputNotFound{
        JPasswordField passwordField;
        try {
            passwordField = passwordFields.get(name);
            // 如果密码输入框不为空，直接返回
            // 如果密码输入框为空，抛出异常
            if (passwordField != null) return passwordField;
            throw new NullPointerException("Password field with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found password field with key " + name, e);
        }
    }

    /**
     * 获取Lable
     * @param name 获取到Lable名称，以键的方式存储在labels集合中
     * @return 返回Lable对象
     */
    public JLabel getLabel(String name) throws LabelNotFound{
        JLabel label;
        try {
            label = labels.get(name);
            // 如果Lable不为空，直接返回
            // 如果Lable为空，抛出异常
            if (label != null) return label;
            throw new NullPointerException("Label with key " + name + " is null");
        } catch (NullPointerException e) {
                throw new LabelNotFound("Fail to found label with key " + name, e);
            }
    }

    /**
     * 设置Lable
     * @param name Lable名称
     */
    public void putLabel(String name, JLabel label) {
        labels.put(name, label);
    }

    public StandardUI() {
        // 设置默认布局
        // 初始化GBC对象并设置间隔
        // gbc你有病吧
        this.setLayout(new GridBagLayout());
        this.setBackground(new java.awt.Color(255, 255, 255));
        gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
    }

    /**
     * 获取当前UI对象
     * @return
     */
    public JPanel getThis() {
        return this;
    }

    /**
     * 切换面板
     * @param areaKey 目标区域
     * @param newPanel 将要显示的新面板
     */
    public void switchPanel(String areaKey, JPanel newPanel) {
        JPanel area = getPanel(areaKey);
        area.removeAll();
        area.setLayout(new BorderLayout());
        area.add(newPanel, BorderLayout.CENTER);
        area.revalidate();
        area.repaint();
    }

    protected void setFontSize(JComponent component, int size) {
        Font font = component.getFont();
        component.setFont(font.deriveFont((float)size)); // 只改字号
    }

    protected void setStyle() {
        throw new UnsupportedOperationException("setStyle() not implemented");
    }

}
