package standard;

import javax.swing.*;

import local.error.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;

public abstract class StandardUI extends JPanel {

    public static final short gleft = 0;
    public static final short gright = 1;
    public static final short gmiddle = 0;


    public static final local.utils.UIUtils utils = local.utils.UIUtils.getInstance();


    protected Map<String, AbstractButton> buttons = new LinkedHashMap<String, AbstractButton>();

    protected Map<String, JPanel> panels = new LinkedHashMap<String, JPanel>();

    protected Map<String, JTextField> textFields = new LinkedHashMap<String, JTextField>();

    protected Map<String, BufferedImage> images = new LinkedHashMap<String, BufferedImage>();

    protected Map<String, JCheckBox> checkBoxs = new LinkedHashMap<String, JCheckBox>();

    protected Map<String, JComboBox<String>> comboBoxs = new LinkedHashMap<String, JComboBox<String>>();

    protected Map<String, JPasswordField> passwordFields = new LinkedHashMap<String, JPasswordField>();

    protected Map<String, JLabel> labels = new LinkedHashMap<String, JLabel>();

    protected Map<String, JTextArea> textAreas = new LinkedHashMap<String, JTextArea>();

    protected GridBagConstraints gbc;

    public void putButton(String name, AbstractButton button) {
        buttons.put(name, button);
    }

    public void putPanel(String name, JPanel panel) {
        panels.put(name, panel);
    }


    public void putTextField(String name, JTextField textField) {
        textFields.put(name, textField);
    }


    public void putImage(String name, BufferedImage image) {
        images.put(name, image);
    }


    public void putCheckBox(String name, JCheckBox checkBox) {
        checkBoxs.put(name, checkBox);
    }


    public void putComboBox(String name, JComboBox<String> comboBox) {
        comboBoxs.put(name, comboBox);
    }


    public void putPasswordField(String name, JPasswordField passwordField) {
        passwordFields.put(name, passwordField);
    }

    public void putTextArea(String name, JTextArea textArea) {
        textAreas.put(name, textArea);
    }
    

    public JTextArea getTextArea(String name) throws TextAreaNotFound{
        JTextArea textArea;
        try {
            textArea = textAreas.get(name);

            if (textArea != null) return textArea;
            throw new NullPointerException("TextArea with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new ButtonNotFound("Fail to found TextArea with key " + name, e);
        }
    }


    public AbstractButton getButton(String name) throws ButtonNotFound {
        AbstractButton button;
        try {
            button = buttons.get(name);

            if (button != null) return button;
            throw new NullPointerException("Button with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new ButtonNotFound("Fail to found button with key " + name, e);
        }
    }


    public JPanel getPanel(String name) throws PanelNotFound{
        JPanel panel;
        try {
            panel = panels.get(name);
            if (panel != null) return panel;
            throw new NullPointerException("Panel with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new PanelNotFound("Fail to found panel with key " + name, e);
        }
    }

    public JTextField getTextField(String name) throws InputNotFound{
        JTextField inputBox;
        try {
            inputBox = textFields.get(name);

            if (inputBox != null) return inputBox;
            throw new NullPointerException("Input box with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found input box with key " + name, e);
        }
    }


    public BufferedImage getImage(String name) throws ImageNotFound{
        BufferedImage image;
        try {
            image = images.get(name);
            if (image != null) return image;
            throw new NullPointerException("Image with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new ImageNotFound("Fail to found image with key " + name, e);
        }
    }

    public JCheckBox getCheckBox(String name) throws InputNotFound{
        JCheckBox checkBox;
        try {
            checkBox = checkBoxs.get(name);

            if (checkBox != null) return checkBox;
            throw new NullPointerException("Checkbox with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found checkbox with key " + name, e);
        }
    }

    public JComboBox<String> getComboBox(String name) throws InputNotFound{
        JComboBox<String> comboBox;
        try {
            comboBox = comboBoxs.get(name);

            if (comboBox != null) return comboBox;
            throw new NullPointerException("Combo box with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found combobox with key " + name, e);
        }
    }

    public JPasswordField getPasswordField(String name) throws InputNotFound{
        JPasswordField passwordField;
        try {
            passwordField = passwordFields.get(name);
            if (passwordField != null) return passwordField;
            throw new NullPointerException("Password field with key " + name + " is null");
        } catch (NullPointerException e) {
            throw new InputNotFound("Fail to found password field with key " + name, e);
        }
    }

    public JLabel getLabel(String name) throws LabelNotFound{
        JLabel label;
        try {
            label = labels.get(name);
            if (label != null) return label;
            throw new NullPointerException("Label with key " + name + " is null");
        } catch (NullPointerException e) {
                throw new LabelNotFound("Fail to found label with key " + name, e);
            }
    }

    public void putLabel(String name, JLabel label) {
        labels.put(name, label);
    }

    public StandardUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new java.awt.Color(255, 255, 255));
        gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
    }

    public JPanel getThis() {
        return this;
    }

    public void switchPanel(String areaKey, JPanel newPanel) {
        JPanel area = getPanel(areaKey);
        area.removeAll();
        area.setLayout(new BorderLayout());
        area.add(newPanel, BorderLayout.CENTER);
        area.revalidate();
        area.repaint();
    }

    protected void setFontSize(JComponent component, int size) {
        Font font = component.getFont();
        component.setFont(font.deriveFont((float)size));
    }

    protected void setStyle() {
        throw new UnsupportedOperationException("setStyle() not implemented");
    }

}
