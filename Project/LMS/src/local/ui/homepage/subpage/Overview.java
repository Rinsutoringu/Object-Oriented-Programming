package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import database.db.DataBaseUtils;
import database.errorhandle.*;

/**
 * 这个页面是个概览页面，显示一些基本信息
 * 比如注册用户数量、物品数量等
 * HelloPage本身是个综合性欢迎页，而这个页面将更多地体现有用的信息
 */
public class Overview extends JPanel {
    private DataBaseUtils dbUtils;
    private errorHandler eh;

    public int NumOfReg;
    public int NumOfObj;

    public Overview() {

        // 初始化工具类
        dbUtils = DataBaseUtils.getInstance();
        eh = errorHandler.getInstance();

        // this.NumOfReg = getNumOfUsers();
        // this.NumOfObj = getNumOfObjects();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = "Test User";

        JTextArea textArea = new JTextArea("Loading data, please wait...");
        this.add(textArea);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // 在后台线程中执行耗时操作
                NumOfReg = getNumOfUsers();
                NumOfObj = getNumOfObjects();
                return null;
            }

            @Override
            protected void done() {
                // 在事件调度线程中更新UI
                textArea.setText(
                    "Good morning, " + username + "\n" +
                    "Until now, you have registered " + NumOfReg + " users\n" +
                    "Now is Overview page\n" +
                    "Until now, you have recorded " + NumOfObj + " objects"
                );
                repaint();
            }
        };
        worker.execute();

        this.add(new JTextArea(
            "Good morning, " + username + "\n" +
            "Until now, you have registered " + this.NumOfReg + " users\n" +
            "Now is Overview page\n" +
            "Until now, you have recorded " + this.NumOfObj + " objects"));
    }


    /**
     * 获取数据库中的注册用户数量
     * @return 用户数量
     */
    private int getNumOfUsers() {
        String checkmsg = "SELECT COUNT(*) FROM staff";
        try {
            ResultSet rs = dbUtils.SearchDB(checkmsg);
            // 检查 ResultSet 是否有数据
            if (rs != null && rs.next()) {
                return rs.getInt(1); // 获取第一列的计数值
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        // 如果 ResultSet 为空或查询失败，返回默认值 0
        return 0;
    }

    /**
     * 获取数据库中记录的物资量
     * @return 物资量
     */
    private int getNumOfObjects() {
        String checkmsg = "SELECT COUNT(*) FROM Shelf";
        try {
            ResultSet rs = dbUtils.SearchDB(checkmsg);
            // 检查 ResultSet 是否有数据
            if (rs != null && rs.next()) {
                return rs.getInt(1); // 获取第一列的计数值
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        // 如果 ResultSet 为空或查询失败，返回默认值 0
        return 0;
    }
}
