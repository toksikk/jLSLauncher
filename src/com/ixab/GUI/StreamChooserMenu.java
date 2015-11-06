package com.ixab.GUI;

import com.ixab.*;
import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.StreamHandling.StreamOpener;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StreamChooserMenu {
    private JPanel panel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JButton hinzufügenButton;
    private JButton streamAbspielenButton;
    private JButton entfernenButton;

    public StreamChooserMenu() {
        this.initComboBoxes();
        streamAbspielenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StreamOpener so = new StreamOpener(ConfigFileInstanceHandler.getConfig(), ConfigFileInstanceHandler.getConfig().getStreams().indexOf(comboBox1.getSelectedItem()), comboBox2.getSelectedItem().toString());
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
                ConfigFileInstanceHandler.getConfig().removeStream(comboBox1.getSelectedItem());
                ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
                initStreamsComboBox();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: implement twitch api and update stream info
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
        comboBox1.removeAllItems();
        for (String streamName :
                ConfigFileInstanceHandler.getConfig().getStreams()) {
            comboBox1.addItem(streamName);
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
