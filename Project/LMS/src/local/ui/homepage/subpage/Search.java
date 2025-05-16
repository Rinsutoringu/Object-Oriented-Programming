package local.ui.homepage.subpage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.db.DataBase;

/**
 * 物品搜索页面
 * 这个页面是个搜索页面，用户可以在这里搜索物品
 * 物品搜索逻辑在这里实现
 */
public class Search extends JPanel {

    private int NumOfObj;
    private String NameOfObj;
    private static DataBase db = DataBase.getInstance();

    public Search() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username = "Test User";
        this.add(new JLabel("Good morning, "+username));
        this.add(new JLabel("Now is Search page"));
    }

    // TODO 物品搜索逻辑，记得做个异常
    /**
     * 物品搜索逻辑
     * @param objName 物品名称
     * @param objNum 物品数量
     * @return true if the object is found, false otherwise
     */
    public boolean searchObj(String objName, int objNum) {
        // ResultSet rs = db.SearchDB(objName);
        // TODO 此处待完善文件查找逻辑
        return false;
    }
}
