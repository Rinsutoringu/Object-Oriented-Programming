package standard;

import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import local.error.GUIActionFailed;

public abstract class StandardUILogical extends StandardUI {

    /**
     * 默认视图
     * 在这个方法中加入该UI启动时显示的视图
     */
    protected abstract void defaultView();

    /**
     * 为按钮注册点击事件
     * @return 是否成功添加事件
     */
    protected abstract void addButtonAction();
    
    /**
     * 把指定面板的内容替换为指定元素（如为空则直接加入）
     * @param showPanel 目标面板
     * @param showthings 待加入面板的元素
     */
    protected void show(JPanel showPanel, JPanel showthings) {
        // DEBUG
        showPanel.removeAll();
        showPanel.add(showthings);
        showPanel.revalidate(); // 刷新布局
        showPanel.repaint();    // 重绘界面
    }

    /**
     * 把指定面板的内容替换为指定元素（如为空则直接加入）
     * @param showPanel 目标面板
     * @param showthings 待加入面板的元素
     * @param constraints 要加入到什么地方
     */
    protected void show(JPanel showPanel, JPanel showthings, Object constraints) {
        // DEBUG
        showPanel.removeAll();

        if (constraints != null) showPanel.add(showthings, constraints);
        else showPanel.add(showthings);

        showPanel.revalidate(); // 刷新布局
        showPanel.repaint();    // 重绘界面
    }

    /**
     * 清空指定面板内容
     * @param hidePanel 待清空的面板
     */
    protected void clear(JPanel hidePanel) {
        hidePanel.removeAll();
        hidePanel.revalidate(); // 刷新布局
        hidePanel.repaint(); 
    }

    /**
     * 确认当前面板是不是空的
     * @param panel 待查询的面板对象
     * @return 是否为空
     */
    protected boolean isPanelEmpty(JPanel panel) {
        return panel.getComponentCount() == 0 ;
    }

    /**
     * 获取当前UI对象
     * @return
     */
    public abstract JPanel getThis();

    /*以下方法不可以在logical类使用，会炸！ */
    @Override
    public AbstractButton getButton(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void switchPanel(String areaKey, JPanel newPanel) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JPanel getPanel(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JTextField getTextField(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public BufferedImage getImage(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }
    
    @Override
    public JCheckBox getCheckbox(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JComboBox<String> getComboBox(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JPasswordField getPasswordField(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

}