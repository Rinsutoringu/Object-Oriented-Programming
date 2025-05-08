package local.ui.homepage;

import java.awt.event.ActionListener;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import local.ui.homepage.subpage.*;


public class HomePageLogic {
    public static final int DEFAULT = 0;
    private JPanel detaiJpanel;
    private JPanel overJPanel;
    private JPanel topviewPanel;
    HomePageUI homepage;

    public HomePageLogic() {
        homepage = new HomePageUI();

        this.detaiJpanel = homepage.getdetaiJPanel();
        this.overJPanel = homepage.getoverJPanel();
        this.topviewPanel = homepage.getTopviewPanel();
    
        showDetail();
        addButtonAction();
    }

    // 默认视图
    private void showDetail() {
        show(detaiJpanel, new HelloPage());
        show(overJPanel, new HelloPage());
    }

    // 为按钮注册点击事件
    private void addButtonAction() {
        // OverView页面
        homepage.getButton("overview").addActionListener(e ->{
            show(detaiJpanel, new SideBar());
            show(overJPanel, new Overview());
        });

        // search页面
        homepage.getButton("search").addActionListener(e ->{
            show(detaiJpanel, new SideBar());
            show(overJPanel, new Search());
        });

        // stock页面
        homepage.getButton("stock").addActionListener(e ->{
            show(detaiJpanel, new SideBar());
            show(overJPanel, new Stock());
        });
                
        // To do页面
        homepage.getButton("todo").addActionListener(e ->{
            show(detaiJpanel, new ToDoSideBar());
            show(overJPanel, new UserToDo());
        });
    }

    /**
     * 把指定面板的内容替换为指定元素（如为空则直接加入）
     * @param showPanel 目标面板
     * @param showthings 待加入面板的元素
     */
    private void show(JPanel showPanel, JPanel showthings) {
        showPanel.removeAll();
        showPanel.add(showthings);
        showPanel.revalidate(); // 刷新布局
        showPanel.repaint();    // 重绘界面
    }
    
    /**
     * 清空指定面板内容
     * @param hidePanel 待清空的面板
     */
    private void clear(JPanel hidePanel) {
        hidePanel.removeAll();
        hidePanel.revalidate(); // 刷新布局
        hidePanel.repaint(); 
    }

    /**
     * 确认当前面板是不是空的
     * @param panel 待查询的面板对象
     * @return 是否为空
     */
    private boolean isPanelEmpty(JPanel panel) {
        return panel.getComponentCount() == 0 ;
    }
    
    public JPanel getHomePage() {
        return homepage;
    }




}

    