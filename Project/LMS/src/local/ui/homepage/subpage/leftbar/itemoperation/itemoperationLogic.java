package local.ui.homepage.subpage.leftbar.itemoperation;

import java.sql.ResultSet;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.homepage.HomePageLogic;
import local.ui.homepage.subpage.rightbar.overview.OverviewLogic;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class itemoperationLogic extends StandardUILogical {

    private itemoperationUI sidebarui;
    private HomePageLogic homepagelogic;
    private errorHandler eh = errorHandler.getInstance();
    private DataBase dbutils = DataBase.getInstance();
    private JTable table;

    public itemoperationLogic(HomePageLogic homepagelogic) {
        super();
        try {
            sidebarui = new itemoperationUI();
            this.homepagelogic = homepagelogic;
            this.table = getTable();
            setStyle();
            putPL("sidebar", getThis().getPanel("sidebar"));
            defaultView();
            addButtonAction();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    protected void setStyle() {
        getThis().getTextField("result").setEditable(false);
    }

    @Override
    public void defaultView() {
        try {
            show(getThis(), getPL("sidebar"));
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
    }

    @Override
    public void addButtonAction() {
        sidebarui.getButton("submit").addActionListener(e -> {
            new javax.swing.SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    try {
                        String userInput = getUserInput();
                        int number = getNumberInput();
                        if (userInput.isEmpty() || number == -1) return null;

                        Map<String, Object> whereMap = new java.util.HashMap<>();
                        whereMap.put("obj_name", userInput);
                        Map<String, Object> updateMap = new java.util.HashMap<>();
                        updateMap.put("obj_number", number);
                        ResultSet rs = null;
                        try {
                            rs = dbutils.queryRow(GlobalVariables.getShelfTableName(), whereMap);
                            if (rs == null || !rs.next()) {
                                updateMap.put("obj_name", userInput);
                                updateMap.put("obj_lastuptime", new java.sql.Timestamp(System.currentTimeMillis()));
                                updateMap.put("lastuser", GlobalVariables.getUserName());
                                dbutils.insertRow(GlobalVariables.getShelfTableName(), updateMap);
                            } else {
                                int newNumber = rs.getInt("obj_number") + number;
                                if (newNumber < 0) {
                                    javax.swing.SwingUtilities.invokeLater(() -> 
                                        new MiniOption("Input Error", "Object number cannot be negative.", JOptionPane.INFORMATION_MESSAGE)
                                    );
                                    return null;
                                }
                                updateMap.put("obj_number", newNumber);
                                updateMap.put("obj_name", userInput);
                                updateMap.put("obj_lastuptime", new java.sql.Timestamp(System.currentTimeMillis()));
                                updateMap.put("lastuser", GlobalVariables.getUserName());
                            }
                            dbutils.updateRow(GlobalVariables.getShelfTableName(), whereMap, updateMap);

                        } catch (Exception ex) {
                            CatchException.handle(ex, eh);      
                        } finally {
                            if (rs != null) {
                                try {
                                    rs.close();
                                    javax.swing.SwingUtilities.invokeLater(() -> {
                                        getOverviewLogic().refreshTableData(null, true);
                                    });
                                } catch (Exception ex) {
                                    CatchException.handle(ex, eh);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                    }
                    return null;
                }
            }.execute();
        });

        sidebarui.getButton("delete").addActionListener(e -> {
            new SwingWorker<Void, Void>() {
                int selectedRow = table.getSelectedRow();
                @Override
                protected Void doInBackground() {
                    try {
                        String userInput = getUserInput();
                        if (userInput.isEmpty()) return null;
                        if (selectedRow == -1) return null;
                        if (table.isEditing()) {
                            javax.swing.SwingUtilities.invokeAndWait(() -> table.getCellEditor().stopCellEditing());
                        }
                        Map<String, Object> whereMap = new java.util.HashMap<>();
                        whereMap.put("obj_name", userInput);
                        dbutils.deleteRow(GlobalVariables.getShelfTableName(), whereMap);
                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                    }
                    return null;
                }
                @Override
                protected void done() {
                    try {
                        if (selectedRow == -1) {
                            new MiniOption("Delete Error", "Please select a row to delete.", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        DefaultTableModel model = getTableModel();
                        if (model != null && selectedRow < model.getRowCount()) {
                            model.removeRow(selectedRow);
                        }
                        getOverviewLogic().refreshTableData(null, true);
                    } catch (Exception ex) {
                        CatchException.handle(ex, eh);
                    }
                }
            }.execute();
        });

        sidebarui.getButton("search").addActionListener(e->{
            ResultSet rs = null;
            try {
                String userSearch = getUserSearch();
                if (userSearch.isEmpty()) return;

                Map<String, Object> whereMap = new java.util.HashMap<>();
                whereMap.put("obj_name", userSearch);
                rs = dbutils.queryRow(GlobalVariables.getShelfTableName(), whereMap);
                if (rs == null || !rs.next()) {
                    new MiniOption("Search Error", "No results found.", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                getThis().getTextField("result").setText(
                    "Item amount:"+ rs.getString("obj_number")
                );
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        sidebarui.getButton("clear").addActionListener(e->{
            try {
                getThis().getTextField("result").setText("");
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        sidebarui.getButton("plus1").addActionListener(e->{
            try {
                int numberInput = getNumberInput();
                numberInput++;
                setNumber(String.valueOf(numberInput));
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        sidebarui.getButton("minus1").addActionListener(e->{
            try {
                int numberInput = getNumberInput();
                setNumber(String.valueOf(numberInput - 1));
            } catch (Exception ex) {
                CatchException.handle(ex, eh);
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                int rowCount = table.getRowCount();
                if (selectedRow != -1 && selectedRow < rowCount) {
                    setUserInput(String.valueOf(table.getValueAt(selectedRow, 0)));
                }
            }
        });
    }

    private String getUserSearch() {
        String userInput = getThis().getTextField("search").getText();
        if (userInput.isEmpty()) {
            new MiniOption("Input Error", "Please enter a value.", JOptionPane.INFORMATION_MESSAGE);
        }
        return userInput;
    }

    private String getUserInput() {
        String userInput = getThis().getTextField("objectname").getText();
        if (userInput.isEmpty()) {
            new MiniOption("Input Error", "Please enter a value.", JOptionPane.INFORMATION_MESSAGE);
        }
        return userInput;
    }

    private void setUserInput(String userInput) {
        getThis().getTextField("objectname").setText(userInput);
    }

    private int getNumberInput() {
        String numberInput = getThis().getTextField("objectnumber").getText();
        if (numberInput.isEmpty()) {
            new MiniOption("Input Error", "Please enter a number.", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
        try {
            return Integer.parseInt(numberInput);
        } catch (NumberFormatException ex) {
            new MiniOption("Input Error", "Please enter a valid number.", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }

    private void setNumber(String numberInput) {
        getThis().getTextField("objectnumber").setText(numberInput);
    }

    private OverviewLogic getOverviewLogic() {
        homepagelogic.getPage("overview");
        return (OverviewLogic) homepagelogic.getPage("overview");
    }

    private DefaultTableModel getTableModel() {
        try {
            return (DefaultTableModel) getOverviewLogic().getThis().getTableModel();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    private JTable getTable() {
        try {
            return getOverviewLogic().getThis().getTable();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    @Override
    public itemoperationUI getThis() {
        return sidebarui;
    }
}
