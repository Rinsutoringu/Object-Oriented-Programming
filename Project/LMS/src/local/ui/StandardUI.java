package local.ui;

import javax.swing.*;

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
    public static final local.utils.UIUtils utils = new local.utils.UIUtils();

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

    protected GridBagConstraints gbc;

    /**
     * 获取按钮
     * @param name 获取到按钮名称，以键的方式存储在buttons集合中
     * @return 返回按钮对象
     */
    public AbstractButton getButton(String name) {
        return buttons.get(name);
    }

    /**
     * 获取面板
     * @param name 获取到面板名称，以键的方式存储在panels集合中
     * @return 返回面板对象
     */
    public JPanel getPanel(String name) {
        return panels.get(name);
    }

    /**
     * 获取输入框
     * @param name 获取到输入框名称，以键的方式存储在inputBoxs集合中
     * @return 返回输入框对象
     */
    public JTextField getTextField(String name) {
        return inputBoxs.get(name);
    }

    /**
     * 获取图片
     * @param name 获取到图片名称，以键的方式存储在images集合中
     * @return 返回图片对象
     */
    public BufferedImage getImage(String name) {
        return images.get(name);
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
}
