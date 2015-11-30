package com.ixab.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel message;

    public AlertWindow(String s) {
        message.setText(s);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }

    public void close() {
        dispose();
    }

    public static void main(String[] args) {
        AlertWindow dialog = new AlertWindow("unused main() in alertwindow");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    public static AlertWindow main(String s) {
        AlertWindow dialog = new AlertWindow(s);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
        return dialog;
    }
}
