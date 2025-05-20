package standard;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import local.error.GUIActionFailed;

public abstract class StandardUILogical extends StandardUI {

    protected abstract void defaultView();

    protected abstract void addButtonAction();

    protected void show(JPanel showPanel, JPanel showthings) {
        showPanel.removeAll();
        showPanel.add(showthings);
        showPanel.revalidate();
        showPanel.repaint();
    }

    protected void show(JPanel showPanel, JPanel showthings, Object constraints) {
        showPanel.removeAll();

        if (constraints != null) showPanel.add(showthings, constraints);
        else showPanel.add(showthings);

        showPanel.revalidate();
        showPanel.repaint();
    }

    protected void clear(JPanel hidePanel) {
        hidePanel.removeAll();
        hidePanel.revalidate();
        hidePanel.repaint();
    }

    protected boolean isPanelEmpty(JPanel panel) {
        return panel.getComponentCount() == 0 ;
    }

    public abstract JPanel getThis();

    @Override
    public void putButton(String name, AbstractButton button) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putPanel(String name, JPanel panel) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putTextField(String name, JTextField textField) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putImage(String name, BufferedImage image) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putCheckBox(String name, JCheckBox checkBox) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putComboBox(String name, JComboBox<String> comboBox) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void putPasswordField(String name, JPasswordField passwordField) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public void switchPanel(String areaKey, JPanel newPanel) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public AbstractButton getButton(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JPanel getPanel(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JTextField getTextField(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public BufferedImage getImage(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JCheckBox getCheckBox(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JComboBox<String> getComboBox(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    @Override
    public JPasswordField getPasswordField(String name) {
        throw new GUIActionFailed("This method is not supported in StandardUILogical");
    }

    protected Map<String, StandardUILogical> PageRegistry = new HashMap<>();

    public void putPage(String name, StandardUILogical PageName) {
        try {
            this.PageRegistry.put(name, PageName);
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to register Page object", e);
        }
    }

    public JPanel getPage(String plName, String pageName) {
        try {
            StandardUILogical ui = this.PageRegistry.get(plName);
            if (ui != null) {
                StandardUI standardUI = (StandardUI) ui.getThis();
                JPanel panel = standardUI.getPanel(pageName);

                return panel;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to get Page object", e);
        }
        return null;
    }

    public StandardUILogical getPage(String plName) {
        try {
            StandardUILogical ui = this.PageRegistry.get(plName);
            if (ui != null) {
                return ui;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to get Page object", e);
        }
        return null;
    }

    protected Map<String, JPanel> plRegistry = new HashMap<>();

    public void putPL(String name, JPanel ui) {
        try {
            this.plRegistry.put(name, ui);
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to register PL object", e);
        }
    }

    public JPanel getPL(String name) {
        try {
            JPanel pl = this.plRegistry.get(name);
            if (pl != null) {
                return pl;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to get PL object", e);
        }
        return null;
    }

    protected Map<String, JPanel> cpRegistry = new HashMap<>();

    public void putCP(String name, JPanel cp) {
        try {
            this.cpRegistry.put(name, cp);
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to register CP object", e);
        }
    }

    public JPanel getCP(String name) {
        try {
            JPanel cp = this.cpRegistry.get(name);
            if (cp != null) {
                return cp;
            }
        } catch (Exception e) {
            throw new GUIActionFailed("Failed to get CP object", e);
        }
        return null;
    }

}