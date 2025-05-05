package local.ui.mainwindow;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


// Create basic frame
public class MainWindowUI {
    private JFrame frame;
    private JPanel mainPanel;
    public MainWindowUI()  {
        frame = new JFrame("LMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // 建立主Panel，简化布局管理
        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // 设置默认可见性
        frame.setVisible(true);


    }
    
    /**
     * 给主面板增加子面板，默认位置在CENTER
     * @param 想要增加的子面板的对象名称
     * @return 是否增加成功
     */
    public boolean addPanel(JPanel subpanel) {
        try {
            mainPanel.add(subpanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}