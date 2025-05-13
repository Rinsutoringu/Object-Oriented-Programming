package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Overview extends JPanel {
    public Overview() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // TODO 从数据库获取用户名
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        this.add(new JLabel("Now is Overview page"));
        
    }

    /**
     * 获取数据库中的注册用户数量
     * @return 用户数量
     */
    private int getNumOfUsers() {
        // TODO 从数据库获取用户数量
        return 100;
    }

    /**
     * 获取数据库中记录的物资量
     * @return 物资量
     */
    private int getNumOfObjects() {
        // TODO 从数据库获取物资量
        return 1000;
    }
}
