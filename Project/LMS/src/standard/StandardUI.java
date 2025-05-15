package standard;

import javax.swing.*;

import local.error.*;

import java.util.HashMap;
import java.util.Map;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;

/**
 * 标准UI类
 * 该类是所有UI类的父类，提供了一些通用的方法和属性
 * 主要用于存储按钮、面板、输入框和图片等组件
 * 以及提供获取这些组件的方法
 */

public abstract class StandardUI extends JPanel {

    
    /**
     * 工具类
     */
    public static final local.utils.UIUtils utils = local.utils.UIUtils.getInstance();

    /**
     * 按钮集合
     */
    protected Map<String, AbstractButton> buttons = new HashMap<String, AbstractButton>();
    /**
     * 面板集合
     */
    protected Map<String, JPanel> panels = new java.util.HashMap<String, JPanel>();
    /**
     * 输入框集合
     */
    protected Map<String, JTextField> inputBoxs = new java.util.HashMap<String, JTextField>();
    /**
     * 图片集合
     */
    protected Map<String, BufferedImage> images = new java.util.HashMap<String, BufferedImage>();
    /**
     * 选择框集合
     */
    protected Map<String, JCheckBox> checkBoxs = new java.util.HashMap<String, JCheckBox>();

    protected GridBagConstraints gbc;

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
            inputBox = inputBoxs.get(name);
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
    public JCheckBox getCheckbox(String name) throws InputNotFound{
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
    public abstract JPanel getThis();

}
