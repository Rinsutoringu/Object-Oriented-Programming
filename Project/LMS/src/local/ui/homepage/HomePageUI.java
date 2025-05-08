package local.ui.homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class HomePageUI extends JPanel {
    private JPanel topview;
    private JPanel detaiJPanel;
    private JPanel overJPanel;

    private local.utils.UIUtils utils = new local.utils.UIUtils();

    public HomePageUI() {

        topview = topview();
        detaiJPanel = detaiJPanel();
        overJPanel = overJPanel();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);

        utils.addComponent(this, topview, gbc, 0, 0,1, 0.1,
        GridBagConstraints.BOTH, 2, 1);
        utils.addComponent(this, detaiJPanel, gbc, 0, 1, 1, 0.9,
        GridBagConstraints.BOTH, 1, 1);
        utils.addComponent(this, overJPanel, gbc, 1, 1, 2.5, 0.9,
        GridBagConstraints.BOTH, 1, 1);

    }
    private Map<String, JButton> buttons = new HashMap<String, JButton>();

    // 顶栏
    private JPanel topview() {
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
        for (JButton button : buttons.values()) {
            panel.add(button);
            panel.add(Box.createHorizontalStrut(buttonGap));
        }
        panel.add(Box.createHorizontalGlue());

        // 背景
        panel.setBackground(new Color(255,255,255));
        return panel;
    }
    
    private JButton topButton(String ButtonText) {
        JButton button = new JButton();
        button.setText(ButtonText);
        return button;        
    }


    public JButton getButton(String ButtonName) {
        return buttons.get(ButtonName);
    }

    // 大的，主，总览栏
    private JPanel overJPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // panel.setPreferredSize(new Dimension(100, 100));   

        panel.setBackground(Color.BLUE);        
        return panel;
    }

    // 小的，条目一览栏
    private JPanel detaiJPanel() {  
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.ORANGE);     
        panel.setPreferredSize(new Dimension(100, 100));   
        return panel;
    }

    public JPanel getoverJPanel() {
        return overJPanel;
    }

    public JPanel getdetaiJPanel() {
        return detaiJPanel;
    }
}
