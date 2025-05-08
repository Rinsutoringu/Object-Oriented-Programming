package local.ui.homepage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelloPage extends JPanel {
    public HelloPage() {
        // TODO 从数据库获取用户名
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        
    }
}
