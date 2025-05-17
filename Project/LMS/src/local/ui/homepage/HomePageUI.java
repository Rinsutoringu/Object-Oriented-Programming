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
            init_homepagePL();
            utils.addComponent( this, this.getPanel("homepage"), gbc, 1, 1);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }

        // 界面的基本布局已在StandardUI中完成
        // 添加组件

    }


    private void init_homepagePL() {
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        create_topCP();
        create_detailCP();
        create_overCP();
        utils.addComponent(this, panels.get("topview"), gbc, 0, 0,1, 0.1,
        GridBagConstraints.BOTH, 2, 1);
        utils.addComponent(this, panels.get("details"), gbc, 0, 1, 1, 0.9,
        GridBagConstraints.BOTH, 1, 1);
        utils.addComponent(this, panels.get("overview"), gbc, 1, 1, 2.5, 0.9,
        GridBagConstraints.BOTH, 1, 1);

        putPanel("homepage", panel);
    }

    // 顶栏
    private void create_topCP() {

        JPanel panel = new JPanel();
        // 设置topview建议间隔
        panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); 
        
        buttons.put("overview", buildButton("OverView"));
        buttons.put("operation", buildButton("Operation"));
        buttons.put("stock", buildButton("Stock-in"));
        buttons.put("todo", buildButton("My ToDo"));

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

    // 右栏(大的那个)
    private void create_overCP() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.BLUE);        
            panels.put("overview", panel);
    }

    // 左栏(小的那个)
    private void create_detailCP() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.ORANGE);
            panel.setPreferredSize(new Dimension(100, 100));
            panels.put("details", panel);
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
