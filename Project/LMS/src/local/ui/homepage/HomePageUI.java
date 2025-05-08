package local.ui.homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JButton overview;
    private JButton search;
    private JButton stock;
    private JButton todo;

    // 顶栏
    private JPanel topview() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); 
        
        overview = topButton("OverView");
        search = topButton("Search Lab");
        stock = topButton("Stock-in");
        todo = topButton("My ToDo");

        panel.add(Box.createHorizontalGlue());
        panel.add(overview);
        panel.add(Box.createHorizontalStrut(20)); // 按钮之间的间距
        panel.add(search);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(stock);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(todo);
        panel.add(Box.createHorizontalGlue());
        
        panel.setBackground(new Color(255,255,255));
        return panel;
    }
    
    private JButton topButton(String ButtonText) {
        JButton button = new JButton();
        button.setText(ButtonText);
        return button;        
    }

    public JButton getOverviewButton() {
        return overview;
    }
    
    public JButton getSearchButton() {
        return search;
    }
    
    public JButton getStockButton() {
        return stock;
    }
    
    public JButton getTodoButton() {
        return todo;
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
