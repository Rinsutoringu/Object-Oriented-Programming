package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 物品存放页面
 * 这个页面是个存放页面，用户可以在这里存放物品
 * 物品存放逻辑在这里实现
 */
public class Stock extends JPanel {
    public Stock() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        this.add(new JLabel("Now is Stock page"));
    }

    // TODO 物品存放逻辑
    public boolean putObj(String objName, int objNum) {
        return false;
    }
}