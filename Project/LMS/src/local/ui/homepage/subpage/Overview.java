package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import database.db.DataBaseUtils;
import database.errorhandle.*;

public class Overview extends JPanel {
    private DataBaseUtils dbUtils;
    private errorHandler eh;

    public int NumOfReg;
    public int NumOfObj;

    public Overview() {
        dbUtils = new DataBaseUtils();
        eh = new errorHandler();

        this.NumOfReg = getNumOfUsers();
        this.NumOfObj = getNumOfObjects();
        dbUtils.disconnectDB();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // TODO 从数据库获取用户名
        String username = "Test User";
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
