package com.ixab.GUI;

import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigLoadMenu {
    private static JFrame frame;
    private JPanel panel;
    private JTextField textField1;
    private JButton auswählenButton;
    private JButton ladeKonfigurationButton;

    public ConfigLoadMenu() {
        auswählenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser jf = new JFileChooser();
                jf.showOpenDialog(frame);
                textField1.setText(jf.getSelectedFile().getAbsolutePath());
                ladeKonfigurationButton.setText("Lade Konfiguration");
                ladeKonfigurationButton.setEnabled(true);
            }
        });
        ladeKonfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config c = ConfigFileIOHandler.load(textField1.getText());
                if (c == null) {
                    ladeKonfigurationButton.setText("ACHTUNG: Fehler beim Laden, neue Datei wählen!");
                    ladeKonfigurationButton.setEnabled(false);
                } else {
                    ConfigFileInstanceHandler.setConfig(c);
                    StreamChooserMenu.main(null);
                    frame.dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("ConfigLoadMenu");
        frame.setContentPane(new ConfigLoadMenu().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
