package standard;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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
    public void putButton(String name, AbstractButton button) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putPanel(String name, JPanel panel) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putTextField(String name, JTextField textField) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putImage(String name, BufferedImage image) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putCheckBox(String name, JCheckBox checkBox) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putComboBox(String name, JComboBox<String> comboBox) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putPasswordField(String name, JPasswordField passwordField) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void switchPanel(String areaKey, JPanel newPanel) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public AbstractButton getButton(String name) {
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
    public JCheckBox getCheckBox(String name) {
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
    
    /**
     * logic类所持有的Page对象的注册表
     */
    protected Map<String, StandardUILogical> PageRegistry = new HashMap<>();

    /**
     * 注册Page对象
     * @param name 待注册对象的名称
     * @param PageName 待注册的Page对象
     * @exception GUIActionFailed 注册失败
     */
    public void putPage(String name, StandardUILogical PageName) {
        try {
            this.PageRegistry.put(name, PageName);
        } catch (Exception e) {
            throw new GUIActionFailed("为Page对象添加注册失败", e);
        }
    }

    /**
     * 获取Page
     * @param plName 待获取的pl名称
     * @param pageName 目标page名称
     * @return 获取到的Page对象
     * @exception GUIActionFailed 获取失败
     */
    public JPanel getPage(String plName, String pageName) {
        try {
            StandardUILogical ui = this.PageRegistry.get(plName);
            if (ui != null) {
                StandardUI standardUI = (StandardUI) ui.getThis();
                JPanel panel = standardUI.getPanel(pageName);

                return panel;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("获取Page对象失败", e);
        }
        return null;
    }

    /**
     * logic类本身自有的PL对象的注册表
     */
    protected Map<String, JPanel> plRegistry = new HashMap<>();

    /**
     * 注册PL
     * @param name 待注册对象的名称
     * @param ui 待注册的PL对象
     * @exception GUIActionFailed 注册失败
     */
    public void putPL(String name, JPanel ui) {
        try {
            this.plRegistry.put(name, ui);
        } catch (Exception e) {
            throw new GUIActionFailed("为PL对象添加注册失败", e);
        }
    }

    /**
     * 获取PL
     * @param name 待获取对象的名称
     * @return 获取到的PL对象
     * @exception GUIActionFailed 获取失败
     */
    public JPanel getPL(String name) {
        try {
            JPanel pl = this.plRegistry.get(name);
            if (pl != null) {
                return pl;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("获取PL对象失败", e);
        }
        return null;
    }

    /**
     * logic类自有的CP对象的注册表
     */
    protected Map<String, JPanel> cpRegistry = new HashMap<>();

    /**
     * 注册CP
     * @param name 待注册对象的名称
     * @param cp 待注册的CP对象
     * @exception GUIActionFailed 注册失败
     */
    public void putCP(String name, JPanel cp) {
        try {
            this.cpRegistry.put(name, cp);
        } catch (Exception e) {
            throw new GUIActionFailed("为CP对象添加注册失败", e);
        }
    }

    /**
     * 获取CP
     * @param name 待获取对象的名称
     * @return 获取到的CP对象
     * @exception GUIActionFailed 获取失败
     */
    public JPanel getCP(String name) {
        try {
            JPanel cp = this.cpRegistry.get(name);
            if (cp != null) {
                return cp;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("获取CP对象失败", e);
        }
        return null;
    }

}