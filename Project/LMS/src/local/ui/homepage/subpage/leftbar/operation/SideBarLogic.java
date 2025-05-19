package local.ui.homepage.subpage.leftbar.operation;

import java.sql.ResultSet;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import database.db.DataBase;
import database.errorhandle.CatchException;
import database.errorhandle.errorHandler;
import local.ui.homepage.HomePageLogic;
import local.ui.homepage.subpage.rightbar.overview.OverviewLogic;
import local.ui.miniwindow.MiniOption;
import standard.GlobalVariables;
import standard.StandardUILogical;

public class SideBarLogic extends StandardUILogical {

    /**
     * 声明本Logicl类对应的UI类实例句柄
     */
    private SideBarUI sidebarui;

    /**
     * 父页面句柄
     */
    private HomePageLogic homepagelogic;

    /**
     * 声明UI类中包含的PL句柄
     * 用于动态绘制画面
     */
    // private JPanel examplePL;

    // 定义错误处理器
    private errorHandler eh = errorHandler.getInstance();

    private DataBase dbutils = DataBase.getInstance();

    private JTable table;

    /**
     * 构造函数要做的事情：
     * 1. 初始化持有的Page对象
     * 2. 把要用到的可视化组件（Page PL CP）注册到逻辑层
     */
    public SideBarLogic(HomePageLogic homepagelogic) {

        // 注册默认方法
        super();
        try {
            // 初始化本类的UI对象
            sidebarui = new SideBarUI();

            // 初始化本类持有的Page
            // putPage("name", new xxxlogical());

            // 初始化父类句柄
            this.homepagelogic = homepagelogic;

            // 初始化类中持有的句柄
            this.table = getTable();

            // 初始化类中自有的PL（全屏）
            // TODO 并没有PL
            putPL("sidebar", getThis().getPanel("sidebar"));

            // 初始化类中自有的CP（部分显示）
            // TODO 并没有CP
            // putCP("example1", sidebarui.getPanel("example1"));

            // 初始化画面
            defaultView();

            // 初始化点击事件
            addButtonAction();
        } catch (Exception e) {
            // 使用默认错误处理器处理错误
            CatchException.handle(e, eh);
        }
    }

    // 设置启动后的默认视图
    @Override
    public void defaultView() {

        // 设置默认显示内容 第一个值是目标，第二个值是显示的内容
        // 啥都不加就默认显示UI加载完后的内容
        try {
            // TODO 并没有PL
            show(getThis(), getPL("sidebar"));
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        
    }

    // 为按钮注册点击事件
    @Override
    public void addButtonAction() {
        
        // 增改按钮逻辑
    sidebarui.getButton("submit").addActionListener(e -> {
        // 在此定义具体点击事件
        new javax.swing.SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    String userInput = getUserInput();
                    int number = getNumberInput();
                    if (userInput.isEmpty() || number == -1) return null;

                    // 构造键值对
                    Map<String, Object> whereMap = new java.util.HashMap<>();
                    whereMap.put("obj_name", userInput);
                    Map<String, Object> updateMap = new java.util.HashMap<>();
                    updateMap.put("obj_number", number);
                    ResultSet rs = null;
                    try {
                        // 执行数据库操作
                        rs = dbutils.queryRow(GlobalVariables.getShelfTableName(), whereMap);
                        if (rs == null || !rs.next()) {
                            // 如果没有记录，则插入新记录
                            updateMap.put("obj_name", userInput);
                            updateMap.put("obj_lastuptime", new java.sql.Timestamp(System.currentTimeMillis()));
                            updateMap.put("lastuser", GlobalVariables.getUserName());
                            dbutils.insertRow(GlobalVariables.getShelfTableName(), updateMap);
                        } else {
                            // 如果有记录，则更新记录
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
                                // 刷新表格需要在EDT线程
                                javax.swing.SwingUtilities.invokeLater(() -> {
                                    getOverviewLogic().refreshTableData(null, true);
                                });
                            } catch (Exception ex) {
                                CatchException.handle(ex, eh);
                            }
                        }
                    }
                } catch (Exception ex) {
                    // 基础的错误处理逻辑
                    CatchException.handle(ex, eh);
                }
                return null;
            }
        }.execute();
    });

        // 删除按钮逻辑
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
                        // 构造键值对
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

        // 搜索按钮逻辑
        sidebarui.getButton("search").addActionListener(e->{
            ResultSet rs = null;
            // 在此定义具体点击事件
            try {
                System.out.println(getThis().getPanel("addandedit"));
                String userSearch = getUserSearch();
                if (userSearch.isEmpty()) return;

                // 构造键值对
                Map<String, Object> whereMap = new java.util.HashMap<>();
                whereMap.put("obj_name", userSearch);
                rs = dbutils.queryRow(GlobalVariables.getShelfTableName(), whereMap);
                if (rs == null || !rs.next()) {
                    new MiniOption("Search Error", "No results found.", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                getThis().getTextField("result").setText(
                    "Search Result: Item name " + 
                    rs.getString("obj_name") + 
                    ", Number " + 
                    rs.getString("obj_number") + 
                    ", Last Update Time " + 
                    rs.getString("obj_lastuptime") + 
                    ", Last User " + 
                    rs.getString("lastuser")
                );
            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }
        });

        // 清空按钮逻辑
        sidebarui.getButton("clear").addActionListener(e->{
            // 在此定义具体点击事件
            try {
                getThis().getTextField("objectname").setText("");
            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }
        });

        // +1按钮逻辑
        sidebarui.getButton("plus1").addActionListener(e->{
            // 在此定义具体点击事件
            try {
                int numberInput = getNumberInput();
                numberInput++;
                setNumber(String.valueOf(numberInput));
            } catch (Exception ex) {
                // 基础的错误处理逻辑
                CatchException.handle(ex, eh);
            }
        });

        // -1按钮逻辑
        sidebarui.getButton("minus1").addActionListener(e->{
            // 在此定义具体点击事件
            try {
                int numberInput = getNumberInput();
                setNumber(String.valueOf(numberInput - 1));
            } catch (Exception ex) {
                // 基础的错误处理逻辑
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
        // 这里假设 this 指向 HomePageLogic 的实例
        homepagelogic.getPage("overview");
        return (OverviewLogic) homepagelogic.getPage("overview");
    }

    private DefaultTableModel getTableModel() {
        // 这里假设 this 指向 HomePageLogic 的实例
        try {
            return (DefaultTableModel) getOverviewLogic().getThis().getTableModel();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    private JTable getTable() {
        // 这里假设 this 指向 HomePageLogic 的实例
        try {
            return getOverviewLogic().getThis().getTable();
        } catch (Exception e) {
            CatchException.handle(e, eh);
        }
        return null;
    }

    // 获取实例
    @Override
    public SideBarUI getThis() {
        return sidebarui;
    }

}
