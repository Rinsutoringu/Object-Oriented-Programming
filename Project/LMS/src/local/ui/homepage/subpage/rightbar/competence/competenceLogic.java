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
            model.setRowCount(0);

            String sql = "SELECT username, regdate, state FROM staff";
            System.out.println("Executing SQL: " + sql);

            try {
                dbUtils.getConnection();
            } catch (Exception e) {
                System.err.println("Database connection failed: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            java.util.List<Object[]> data = dbUtils.queryCustomShelfTable(sql);

            for (Object[] row : data) {
                String username = (String) row[0];
                String regdate;
                try {
                    long timestamp = Long.parseLong(row[1].toString());
                    regdate = convertTimestampToReadableDate(timestamp);
                } catch (NumberFormatException e) {
                    regdate = row[1].toString();
                }

                int state = (int) row[2];
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

                model.addRow(new Object[]{username, regdate, permissionLevel});
            }
        } catch (Exception e) {
            System.err.println("Error in refreshTableData: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String convertTimestampToReadableDate(long timestamp) {
        java.util.Date date = new java.util.Date(timestamp);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    @Override
    public competenceUI getThis() {
        return competenceui;
    }
}