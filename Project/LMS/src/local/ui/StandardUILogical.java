package local.ui;

import javax.swing.JPanel;

public abstract class StandardUILogical extends StandardUI {

    /**
     * 构造函数，默认显示
     */
    // protected StandardUILogical() {
    //     defaultView();
    //     if (!addButtonAction()) System.out.println("添加按钮事件失败");
    // }

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


    

}
