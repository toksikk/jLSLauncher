package com.ixab.GUI;

import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.StreamHandling.StreamInfo;
import com.ixab.StreamHandling.StreamOpener;

import javax.swing.*;
import java.awt.event.*;

public class MainWindow {
    private JPanel panel;
    private JComboBox comboBoxStreams;
    private JComboBox comboBoxQuality;
    private JButton buttonAddStream;
    private JButton buttonPlayStream;
    private JButton buttonRemoveStream;
    private JTextField textFieldStreamStatus;
    private JTextField textFieldStreamViewers;
    private JTextField textFieldStreamTitle;
    private JTextField textFieldStreamGame;
    private JButton buttonReloadStreamData;
    private JPanel panelPreviewImage;
    private JButton buttonSettings;
    private Thread updateThread;

    protected JLabel getErrorLabel() {
        return errorLabel;
    }

    private JLabel errorLabel;
    protected boolean lockStreamInfoGetter = false;

    public MainWindow() {
        MainWindowGate.setMainWindow(this);
        this.initComboBoxes();
        buttonPlayStream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StreamOpener so = new StreamOpener(ConfigFileInstanceHandler.getConfig(), ConfigFileInstanceHandler.getConfig().getStreams().indexOf(comboBoxStreams.getSelectedItem()), comboBoxQuality.getSelectedItem().toString());
                so.start();
            }
        });
        buttonAddStream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewStream();
            }
        });
        buttonRemoveStream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigFileInstanceHandler.getConfig().removeStream(comboBoxStreams.getSelectedItem());
                ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
                lockStreamInfoGetter = true;
                initStreamsComboBox();
                lockStreamInfoGetter = false;
            }
        });
        comboBoxStreams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!lockStreamInfoGetter) {
                    updateStreamDetails();
                }
            }
        });
        buttonReloadStreamData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStreamDetails();
            }
        });
        buttonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsWindow sw = new SettingsWindow();
                sw.create();
            }
        });
    }
    private void updateStreamDetails() {
        if (updateThread != null) {
            if (updateThread.isAlive()) {
                updateThread.interrupt();
            }
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                StreamInfo.initStreamData(comboBoxStreams.getSelectedItem().toString());
                textFieldStreamStatus.setText(StreamInfo.getStatus());
                textFieldStreamGame.setText(StreamInfo.getGame());
                textFieldStreamViewers.setText(StreamInfo.getViewers());
                textFieldStreamTitle.setText(StreamInfo.getTitle());
                panelPreviewImage.getGraphics().drawImage(StreamInfo.getPreviewImage(),0,0,null);
            }
        };
        Thread t = new Thread(r);
        this.updateThread = t;
        t.start();
        ErrorMessageGate.setErrorText("Lade Stream-Daten von " + comboBoxStreams.getSelectedItem().toString() + "...");
    }
    private void addNewStream() {
        AddStreamDialog as = new AddStreamDialog();
        as.create();
    }
    private void initComboBoxes() {
        initQualityComboBox();
        initStreamsComboBox();
    }
    protected void initStreamsComboBox() {
        comboBoxStreams.removeAllItems();
        for (String streamName :
                ConfigFileInstanceHandler.getConfig().getStreams()) {
            comboBoxStreams.addItem(streamName);
        }
    }
    private void initQualityComboBox() {
        comboBoxQuality.removeAllItems();
        comboBoxQuality.addItem("best"); comboBoxQuality.addItem("high"); comboBoxQuality.addItem("medium"); comboBoxQuality.addItem("low"); comboBoxQuality.addItem("mobile");
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("jLSLauncher "+ com.ixab.Main.getVersion());
        frame.setContentPane(new MainWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
