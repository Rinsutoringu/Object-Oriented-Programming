package local.ui.login.subpage;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUI;

public class setDBconnect extends StandardUI{

    errorHandler eh = errorHandler.getInstance();
    
    public setDBconnect() {

        try {
            init_getdbinfo();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    /**
     * 提供输入框，让用户输入数据库连接信息
     * 连接信息包括：数据库地址、端口号、用户名、密码
     */
    private void init_getdbinfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(80, 200));
        inputBoxs.put("dbAddress", new javax.swing.JTextField(20));
        inputBoxs.put("dbPort", new javax.swing.JTextField(20));
        inputBoxs.put("dbUser", new javax.swing.JTextField(20));
        inputBoxs.put("dbPassword", new javax.swing.JPasswordField(20));

        // 添加占位组件
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(80, 50)); // 设置占位组件的高度
        spacer.setOpaque(false); // 设置为透明
        panel.add(spacer);

        panel.add(new javax.swing.JLabel("Database Address:"));
        panel.add(inputBoxs.get("dbAddress")); 
        panel.add(new javax.swing.JLabel("Database Port:"));
        panel.add(inputBoxs.get("dbPort"));
        panel.add(new javax.swing.JLabel("Database UserName:"));
        panel.add(inputBoxs.get("dbUser"));
        panel.add(new javax.swing.JLabel("Database Password:"));
        panel.add(inputBoxs.get("dbPassword")); 

        panels.put("getDBInfo", panel);
    }
// TODO 实际的检测逻辑
    
    @Override
    public JPanel getThis() {
        return this;
    }
}
