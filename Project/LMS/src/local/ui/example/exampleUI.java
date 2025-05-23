package local.ui.example;

import java.awt.*;

import javax.swing.*;

import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUI;

public class exampleUI extends StandardUI {

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    public exampleUI() {
        
        // 初始化UI并注册默认方法
        super();

        try {
            // 初始化本类自有的PL
            init_examplePL();
            
            // 设置样式
            setStyle();

            // 把本类PL加入到UI
            utils.addComponent(this, getPanel("examplewindow"), gbc, 1, 2);
        } catch (Exception e) {
            // 使用默认错误处理器处理错误
            CatchException.handle(e, eh);
        }
    }
    @Override
    protected void setStyle() {
        // your style settings here
    }

    // 这是一个示范PL单元
    private void init_examplePL() {
        JPanel panel = new JPanel();
        // 设置layout manager
        // panel.setPreferredSize(new Dimension(0, 9));
        panel.setLayout(new GridBagLayout()); 

        // 创建PL单元上需要的组件
        createExample();
        putButton("example", new JButton("Example"));

        // 设置组件到PL单元

        short gheight = 0;

        utils.addComponent(panel, getPanel("example1"), gbc, gmiddle, ++gheight);

        utils.addComponent(panel, getButton("example"), gbc, gmiddle, ++gheight, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        // 注册PL单元
        putPanel("examplewindow", panel);
    }

    // 这是一个示范CP组件
    private void createExample() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); 

        putButton("example1", new JButton("Example1"));

        // 设置组件到CP组件
        utils.addComponent(panel, getButton("example1"), gbc, 1, 1, 1, 1,
        GridBagConstraints.NONE, 1, 1);

        // 注册CP组件
        putPanel("example1", panel);
    }

    @Override
    public exampleUI getThis() {
        return this;
    }
}
