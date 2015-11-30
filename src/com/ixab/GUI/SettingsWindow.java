package com.ixab.GUI;

import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.UserInfoHandling.UserInfo;

import javax.swing.*;
import java.awt.event.*;

public class SettingsWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox debugmodusCheckBox;
    private JTextField textField1;
    private JButton suchenButton;
    private JTextField twitchBenutzernameTextField;
    private JButton buttonImportUserData;

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
        buttonImportUserData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final String old = buttonImportUserData.getText();
                buttonImportUserData.setText("bitte warten...");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        if (UserInfo.initUserFollowData(twitchBenutzernameTextField.getText(), 0)) {
                            UserInfo.getFollowedChannelNames();
                            // TODO: prüfe ob channel schon vorhanden sind
                            for (String name :
                                    UserInfo.getFollowedChannelNames()) {
                                ConfigFileInstanceHandler.getConfig().addStream(name);
                            }
                            ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
                            MainWindowGate.getMainWindow().refreshStreamsComboBox();
                        }
                        buttonImportUserData.setText(old);
                        ErrorMessageGate.setErrorText("Streams von "+twitchBenutzernameTextField.getText()+" hinzugefügt.");
                    }
                };
                Thread t = new Thread(r);
                t.start();
            }
        });
        twitchBenutzernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                twitchBenutzernameTextField.setText("");
            }
        });
    }

    private void onOK() {
        ConfigFileInstanceHandler.getConfig().setLSPath(textField1.getText());
        ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
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
