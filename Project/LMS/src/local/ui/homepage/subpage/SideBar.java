package local.ui.homepage.subpage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import local.utils.UIUtils;

public class SideBar extends JPanel {
    public SideBar() {
        UIUtils utils = new UIUtils();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // TODO 从数据库获取用户名
        this.setBackground(new Color(255,255,255));
        utils.addComponent(this, new JLabel("Side Bar"), gbc, 0, 0);
        utils.addComponent(this, new JButton("Selection 1"), gbc, 0, 1);
        utils.addComponent(this, new JButton("Selection 2"), gbc, 0, 2);
        utils.addComponent(this, new JButton("Selection 3"), gbc, 0,3);
    }
}