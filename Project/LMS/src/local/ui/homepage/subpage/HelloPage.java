package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 这就是个欢迎页，没啥别的了
 * 登录成功就会看到这个页面
 */
public class HelloPage extends JPanel {
    public HelloPage() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = local.utils.GlobalVariables.currentUsr;
        this.add(new JLabel("Good morning, "+username));
        
    }
}
