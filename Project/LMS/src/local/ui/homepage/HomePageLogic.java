package local.ui.homepage;

import java.awt.event.ActionListener;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import local.ui.homepage.HomePageUI;

public class HomePageLogic {
    public static final int DEFAULT = 0;
    private JPanel detaiJpanel;
    private JPanel overJPanel;
    private int Status;
    HomePageUI homepage;
    public boolean isShow = false;

    public HomePageLogic() {

        // new HomePageUI();
        homepage = new HomePageUI();


        this.detaiJpanel = homepage.getdetaiJPanel();
        this.overJPanel = homepage.getoverJPanel();
    
        // TODO 获取当前客户端状态码
    
        this.Status = DEFAULT;
        showDetail();
    }
    
    private void showDetail() {
        if (this.Status == DEFAULT) {
            // JPanel panel = new JPanel();
            // panel.add(new JLabel("12121"));
            
            JButton button = homepage.getOverviewButton();
            button.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (isShow) {
                        hide(detaiJpanel);
                        isShow = false;
                    }
                    else {
                        show(detaiJpanel, new HelloPage());
                        isShow = true;
                    }
                    
                }
            });

        }
        else {
            hide(this.detaiJpanel);
        }
    }

    private void show(JPanel showPanel, JPanel showthings) {
        showPanel.removeAll();
        showPanel.add(showthings);
        showPanel.revalidate(); // 刷新布局
        showPanel.repaint();    // 重绘界面
    }
    
    private void hide(JPanel hidePanel) {
        hidePanel.removeAll();
        hidePanel.revalidate(); // 刷新布局
        hidePanel.repaint(); 
    }

    public JPanel getHomePage() {
        return homepage;
    }




}

    