package local.ui.homepage.subpage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import local.utils.UIUtils;

/**
 * 侧边栏
 * 这个页面是个侧边栏，用户可以在这里选择不同的页面
 * 侧边栏逻辑在这里实现
 * 不出意外的话只有一部分页面才有侧边栏
 */
public class SideBar extends JPanel {
    public SideBar() {
        UIUtils utils = new UIUtils();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(new Color(255,255,255));
        utils.addComponent(this, new JLabel("Side Bar"), gbc, 0, 0);
        utils.addComponent(this, new JButton("Selection 1"), gbc, 0, 1);
        utils.addComponent(this, new JButton("Selection 2"), gbc, 0, 2);
        utils.addComponent(this, new JButton("Selection 3"), gbc, 0,3);
    }
}