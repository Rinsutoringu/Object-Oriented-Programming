package local.ui.homepage;

import javax.swing.*;

import org.xml.sax.ErrorHandler;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.error.UICreateFail;

import java.awt.*;

public class HomePageUI extends local.ui.StandardUI {

    errorHandler eh = errorHandler.getInstance();

    public HomePageUI() throws UICreateFail {
        super();

        // init view
        try {
            init_topview();
            init_detailJPanel();
            init_overJPanel();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

        // 界面的基本布局已在StandardUI中完成
        // 添加组件
        utils.addComponent(this, panels.get("topview"), gbc, 0, 0,1, 0.1,
        GridBagConstraints.BOTH, 2, 1);
        utils.addComponent(this, panels.get("details"), gbc, 0, 1, 1, 0.9,
        GridBagConstraints.BOTH, 1, 1);
        utils.addComponent(this, panels.get("overview"), gbc, 1, 1, 2.5, 0.9,
        GridBagConstraints.BOTH, 1, 1);
    }

    // 顶栏
    private void init_topview() {

        JPanel panel = new JPanel();
        // 设置topview建议间隔
        panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); 
        
        buttons.put("overview", topButton("OverView"));
        buttons.put("search", topButton("Search Lab"));
        buttons.put("stock", topButton("Stock-in"));
        buttons.put("todo", topButton("My ToDo"));

        // 按钮间隔
        int buttonGap = 20;
        // 把按钮加入顶部视图中
        panel.add(Box.createHorizontalGlue());
        for (AbstractButton button : buttons.values()) {
            panel.add(button);
            panel.add(Box.createHorizontalStrut(buttonGap));
        }
        panel.add(Box.createHorizontalGlue());
        // 背景
            panel.setBackground(new Color(255,255,255));
            panels.put("topview", panel);
        }

    // 大的，主，总览栏
    private void init_overJPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.BLUE);        
            panels.put("overview", panel);
    }

    // 小的，条目一览栏
    private void init_detailJPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.ORANGE);
            panel.setPreferredSize(new Dimension(100, 100));
            panels.put("details", panel);
    }
    // 按钮
    private JButton topButton(String ButtonText) {
        JButton button = new JButton();
        button.setText(ButtonText);
        return button;        
    }
    @Override
    public JPanel getThis() {
        return this;
    }
}
