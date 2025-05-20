package local.ui.mainwindow;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainWindowUI extends JFrame {

    private int mouseX, mouseY;
    private static MainWindowUI instance;
    private JPanel mainPanel;

    private MainWindowUI() {
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LMS");
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        this.add(mainPanel);

        this.add(createCustomTitleBar(), BorderLayout.NORTH);
        this.setVisible(true);
    }

    public static MainWindowUI getInstance() {
        if (instance == null) {
            instance = new MainWindowUI();
        }
        return instance;
    }

    private JPanel createCustomTitleBar() {
        JPanel titleBar = new JPanel();
        titleBar.setLayout(new BorderLayout());
        titleBar.setBackground(new Color(241, 241, 241));
        titleBar.setPreferredSize(new Dimension(this.getWidth(), 35));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);

        Dimension buttonSize = new Dimension(58, 35);
        ImageIcon minisizImageIcon = new ImageIcon(getClass().getResource("/resources/miniSize.png"));
        ImageIcon closeImageIcon = new ImageIcon(getClass().getResource("/resources/close.png"));

        JButton minimizeButton = frameButton(minisizImageIcon, buttonSize, false);
        minimizeButton.addActionListener(e -> this.setState(JFrame.ICONIFIED));
        buttonPanel.add(minimizeButton);

        JButton closeButton = frameButton(closeImageIcon, buttonSize, true);
        closeButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(closeButton);

        titleBar.add(buttonPanel, BorderLayout.EAST);

        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        titleBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });

        return titleBar;
    }

    public JButton frameButton(ImageIcon buttonImageIcon, Dimension buttonSize, boolean iSClose) {

        Color originalColor = new Color(241, 241, 241);
        Color closeButtonColor = new Color(232, 17, 35);
        Color otherButtonColor = new Color(229, 229, 229); 
        Color hoverColor;
        
        if (iSClose) {hoverColor = closeButtonColor;}
        else {hoverColor = otherButtonColor;}

        JButton button = new JButton();

        button.setIcon(buttonImageIcon);
        button.setPreferredSize(buttonSize);
        button.setBackground(originalColor);
        
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
                repaint();
            }
        });

        return button;
    }

    public boolean addPanel(JPanel subpanel) {
        try {
            mainPanel.removeAll();
            mainPanel.add(subpanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}