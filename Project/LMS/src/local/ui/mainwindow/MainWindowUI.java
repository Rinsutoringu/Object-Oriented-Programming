package local.ui.mainwindow;

import javax.swing.JFrame;


// Create basic frame
public class MainWindowUI {
    public MainWindowUI()  {
        JFrame frame = new JFrame("LMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}