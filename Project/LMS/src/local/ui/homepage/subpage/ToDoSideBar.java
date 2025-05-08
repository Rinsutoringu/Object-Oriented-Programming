package local.ui.homepage.subpage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import local.utils.UIUtils;

public class ToDoSideBar extends JPanel {
    public ToDoSideBar() {
        UIUtils utils = new UIUtils();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // TODO 从数据库获取用户名
        this.setBackground(new Color(255,255,255));
        utils.addComponent(this, new JLabel("To Do Side Bar"), gbc, 0, 0);
        utils.addComponent(this, new JButton("Selection 1"), gbc, 0, 1);

    }
}
// If you see this message, you are at testPage!
// Play again!
    // A Submit