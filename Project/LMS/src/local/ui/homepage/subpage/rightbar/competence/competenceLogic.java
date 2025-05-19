package local.ui.homepage.subpage.rightbar.competence;

import javax.swing.table.DefaultTableModel;
import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class competenceLogic extends StandardUILogical {

    private competenceUI competenceui;
    private errorHandler eh = errorHandler.getInstance();
    private DataBase dbUtils = DataBase.getInstance();

    public competenceLogic() {
        super();
        try {
            competenceui = new competenceUI();
            defaultView();
            refreshTableData();
            addButtonAction();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    protected void addButtonAction() {
        competenceui.getButton("refresh").addActionListener(e -> refreshTableData());
    }

    @Override
    public void defaultView() {
        try {
            show(getThis(), competenceui.getPanel("competence"));
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    public void refreshTableData() {
        try {
            DefaultTableModel model = competenceui.getTableModel();
            model.setRowCount(0); // 清空表格数据

            // 查询 staff 表数据
            String sql = "SELECT username, regdate, state FROM staff";
            try {
                dbUtils.getConnection();
            } catch (Exception e) {
                try {
                    dbUtils.getConnection();
                } catch (Exception ex) {
                }
            }
            java.util.List<Object[]> data = dbUtils.queryCustomShelfTable(sql);

            for (Object[] row : data) {
                String username = (String) row[0];
                String regdate = row[1].toString();
                int state = (int) row[2];

                // 转换权限等级为文本
                String permissionLevel;
                switch (state) {
                    case 1:
                        permissionLevel = "User";
                        break;
                    case 2:
                        permissionLevel = "Admin";
                        break;
                    case 0:
                        permissionLevel = "Banned";
                        break;
                    default:
                        permissionLevel = "Unknown";
                }

                // 添加到表格
                model.addRow(new Object[]{username, regdate, permissionLevel});
            }
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public competenceUI getThis() {
        return competenceui;
    }
}