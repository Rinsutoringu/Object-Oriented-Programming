package local.ui.homepage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelloPage extends JPanel {
    public HelloPage() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // TODO 从数据库获取用户名
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        
    }
}
