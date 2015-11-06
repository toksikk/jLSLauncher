package com.ixab.GUI;

import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.StreamHandling.StreamInfo;
import com.ixab.StreamHandling.StreamOpener;

import javax.swing.*;
import java.awt.event.*;

public class StreamChooserMenu {
    private JPanel panel;
    private JComboBox comboBoxStreams;
    private JComboBox comboBoxQuality;
    private JTextField textField1;
    private JButton hinzufügenButton;
    private JButton streamAbspielenButton;
    private JButton entfernenButton;
    private JLabel labelStreamStatus;
    private JLabel labelStreamViewers;
    private JLabel labelStreamTitle;
    private JLabel labelStreamGame;
    private JButton neuLadenButton;
    private boolean lockStreamInfoGetter = false;

    public StreamChooserMenu() {
        this.initComboBoxes();
        streamAbspielenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StreamOpener so = new StreamOpener(ConfigFileInstanceHandler.getConfig(), ConfigFileInstanceHandler.getConfig().getStreams().indexOf(comboBoxStreams.getSelectedItem()), comboBoxQuality.getSelectedItem().toString());
                so.start();
            }
        });
        hinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewStream();
            }
        });
        entfernenButton.addActionListener(new ActionListener() {
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
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (textField1.isFocusOwner() && e.getKeyCode() == 10) {
                    addNewStream();
                }
            }
        });
        neuLadenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStreamDetails();
            }
        });
    }
    private void updateStreamDetails() {
        StreamInfo.getStreamInfo(comboBoxStreams.getSelectedItem().toString());
        labelStreamStatus.setText(StreamInfo.getStatus());
        labelStreamGame.setText(StreamInfo.getGame());
        labelStreamViewers.setText(StreamInfo.getViewers());
        labelStreamTitle.setText(StreamInfo.getTitle());
    }
    private void addNewStream() {
        ConfigFileInstanceHandler.getConfig().addStream(textField1.getText());
        ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
        lockStreamInfoGetter = true;
        initStreamsComboBox();
        lockStreamInfoGetter = false;
        textField1.setText("");
    }
    private void initComboBoxes() {
        initQualityComboBox();
        initStreamsComboBox();
    }
    private void initStreamsComboBox() {
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
        JFrame frame = new JFrame("jLSLauncher v"+ com.ixab.Main.getVersion());
        frame.setContentPane(new StreamChooserMenu().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
