package local.ui.homepage.subpage.rightbar.overview;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import standard.StandardUILogical;

public class OverviewLogic extends StandardUILogical {

    private OverviewUI overviewui;
    private JPanel overview;
    private errorHandler eh = errorHandler.getInstance();
    private DataBase dbUtils = DataBase.getInstance();

    public OverviewLogic() {
        super();
        try {
            overviewui = new OverviewUI();
            this.overview = overviewui.getPanel("overview");
            defaultView();
            refreshTableData(null, true);
            addButtonAction();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void defaultView() {
        try {
            show(getThis(), this.overview);
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void addButtonAction() {
        overviewui.getButton("refresh").addActionListener(e -> {
            try {
                refreshTableData(null, true);
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });
        overviewui.getButton("sortNameAsc").addActionListener(e -> refreshTableData("obj_name", true));
        overviewui.getButton("sortNameDesc").addActionListener(e -> refreshTableData("obj_name", false));
        overviewui.getButton("sortNumberAsc").addActionListener(e -> refreshTableData("obj_number", true));
        overviewui.getButton("sortNumberDesc").addActionListener(e -> refreshTableData("obj_number", false));
    }

    public void refreshTableData(String orderBy, boolean asc) {
        try {
            DefaultTableModel model = overviewui.getTableModel();
            model.setRowCount(0);

            String sql = "SELECT obj_name, obj_number, obj_lastuptime, lastuser FROM shelf";
            if (orderBy != null && !orderBy.isEmpty()) {
                sql += " ORDER BY " + orderBy + (asc ? " ASC" : " DESC");
            }

            for (Object[] row : dbUtils.queryCustomShelfTable(sql)) {
                if (row[2] != null) {
                    row[2] = parseDateTime(row[2].toString());
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            // CatchException.handle(e, eh);
        }
    }

    private String parseDateTime(String dateTime) {
        try {
            if (dateTime.matches("\\d+")) {
                long timestamp = Long.parseLong(dateTime);
                return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new java.util.Date(timestamp));
            }

            java.text.SimpleDateFormat isoFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            java.util.Date date = isoFormat.parse(dateTime);
            return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            return dateTime;
        }
    }

    @Override
    public OverviewUI getThis() {
        return overviewui;
    }
}