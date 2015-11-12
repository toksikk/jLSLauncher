package com.ixab.GUI;

import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;

import javax.swing.*;
import java.awt.event.*;

public class AddStreamDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;

    public AddStreamDialog() {
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
    }

    private void onOK() {
        ConfigFileInstanceHandler.getConfig().addStream(textField1.getText());
        ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
        MainWindowGate.getMainWindow().lockStreamInfoGetter = true;
        MainWindowGate.getMainWindow().initStreamsComboBox();
        MainWindowGate.getMainWindow().lockStreamInfoGetter = false;
        ErrorMessageGate.setErrorText("Stream \""+textField1.getText()+"\" hinzugefügt.");
        textField1.setText("");
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void create() {
        AddStreamDialog dialog = new AddStreamDialog();
        dialog.setLocationByPlatform(true);
        dialog.pack();
        dialog.setVisible(true);
    }
}
