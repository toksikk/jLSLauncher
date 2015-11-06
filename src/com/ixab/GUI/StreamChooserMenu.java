package com.ixab.GUI;

import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.StreamHandling.StreamInfo;
import com.ixab.StreamHandling.StreamOpener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StreamChooserMenu {
    private JPanel panel;
    private JComboBox comboBoxStreams;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JButton hinzufügenButton;
    private JButton streamAbspielenButton;
    private JButton entfernenButton;
    private JLabel labelStreamStatus;
    private JLabel labelStreamViewers;
    private JLabel labelStreamTitle;
    private JLabel labelStreamGame;

    public StreamChooserMenu() {
        this.initComboBoxes();
        streamAbspielenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StreamOpener so = new StreamOpener(ConfigFileInstanceHandler.getConfig(), ConfigFileInstanceHandler.getConfig().getStreams().indexOf(comboBoxStreams.getSelectedItem()), comboBox2.getSelectedItem().toString());
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
                initStreamsComboBox();
            }
        });
        comboBoxStreams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: implement twitch api and update stream info
                StreamInfo.getStreamInfo(comboBoxStreams.getSelectedItem().toString());
                labelStreamStatus.setText(StreamInfo.getStatus());
                labelStreamGame.setText(StreamInfo.getGame());
                labelStreamViewers.setText(StreamInfo.getViewers());
                labelStreamTitle.setText(StreamInfo.getTitle());
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
    }
    private void addNewStream() {
        ConfigFileInstanceHandler.getConfig().addStream(textField1.getText());
        ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
        initStreamsComboBox();
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
        comboBox2.removeAllItems();
        comboBox2.addItem("best"); comboBox2.addItem("high"); comboBox2.addItem("medium"); comboBox2.addItem("low"); comboBox2.addItem("mobile");
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
