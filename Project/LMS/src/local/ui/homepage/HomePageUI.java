package local.ui.homepage;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.error.UICreateFail;
import standard.StandardUI;

import java.awt.*;

public class HomePageUI extends StandardUI {

    errorHandler eh = errorHandler.getInstance();

    public HomePageUI() throws UICreateFail {
        super();

        // init view
        try {
            init_homePL();
            utils.addComponent( this, this.getPanel("home"), gbc, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

        // 界面的基本布局已在StandardUI中完成
        // 添加组件

    }


    private void init_homePL() {
        
        JPanel panel = new JPanel();
        // DEBUG
        panel.setPreferredSize(new Dimension(200, 200));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        create_topCP();
        create_subCP();
        create_mainCP();
        utils.addComponent(this, panels.get("top"), gbc, 0, 0,1, 0.1,
        GridBagConstraints.BOTH, 2, 1);
        utils.addComponent(this, panels.get("sub"), gbc, 0, 1, 1, 0.9,
        GridBagConstraints.BOTH, 1, 1);
        utils.addComponent(this, panels.get("main"), gbc, 1, 1, 2.5, 0.9,
        GridBagConstraints.BOTH, 1, 1);

        putPanel("home", panel);
    }

    // 顶栏
    private void create_topCP() {

        JPanel panel = new JPanel();
        // DEBUG
        panel.setPreferredSize(new Dimension(20, 20));
        // 设置topview建议间隔
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); 
        
        buttons.put("operation", buildButton("Overview"));
        buttons.put("briefing", buildButton("Show Briefing"));
        buttons.put("stock", buildButton("Permissions"));

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
            panels.put("top", panel);
        }

    // 右栏(大的那个)
    private void create_mainCP() {

            JPanel panel = new JPanel(new BorderLayout());
            // DEBUG
            panel.setPreferredSize(new Dimension(20, 20));
            panel.setBackground(Color.BLUE);        
            panels.put("main", panel);
    }

    // 左栏(小的那个)
    private void create_subCP() {

            JPanel panel = new JPanel(new BorderLayout());
            // DEBUG
            panel.setPreferredSize(new Dimension(20, 20));
            panel.setBackground(Color.ORANGE);
            panel.setPreferredSize(new Dimension(100, 100));
            panels.put("sub", panel);
    }
    // 按钮
    private JButton buildButton(String ButtonText) {
        JButton button = new JButton();
        button.setText(ButtonText);
        return button;        
    }
    @Override
    public JPanel getThis() {
        return this;
    }
}
