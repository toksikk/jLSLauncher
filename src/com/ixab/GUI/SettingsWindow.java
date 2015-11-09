package com.ixab.GUI;

import com.ixab.ConfigHandling.ConfigFileInstanceHandler;

import javax.swing.*;
import java.awt.event.*;

public class SettingsWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox debugmodusCheckBox;
    private JTextField textField1;
    private JButton suchenButton;

    public SettingsWindow() {
        textField1.setText(ConfigFileInstanceHandler.getConfig().getLSPath());
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        suchenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Livestreamer Programmdatei auswählen");
                jfc.showOpenDialog(contentPane);
                if (jfc.getSelectedFile() != null) textField1.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void create() {
        SettingsWindow dialog = new SettingsWindow();
        dialog.setTitle("Einstellungen");
        dialog.setLocationByPlatform(true);
        dialog.pack();
        dialog.setVisible(true);
    }
}
