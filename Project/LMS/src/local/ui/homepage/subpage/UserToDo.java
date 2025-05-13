package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 用户待办页面
 * 这个页面是个待办页面，用户可以在这里查看待办事项
 * 待办事项逻辑在这里实现
 */
public class UserToDo extends JPanel {
    public UserToDo() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        this.add(new JLabel("Now is User TODO page"));
        
    }
}
