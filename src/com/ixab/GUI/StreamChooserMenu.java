package com.ixab.GUI;

import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.StreamHandling.StreamOpener;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                ConfigFileInstanceHandler.getConfig().addStream(textField1.getText());
                ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
                initComboBoxes();
                textField1.setText("");
            }
        });
        entfernenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigFileInstanceHandler.getConfig().removeStream(comboBox1.getSelectedItem());
                ConfigFileIOHandler.save(ConfigFileInstanceHandler.getConfig());
                initComboBoxes();
            }
        });
    }
    public void initComboBoxes() {
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
        for (String streamName :
                ConfigFileInstanceHandler.getConfig().getStreams()) {
            comboBox1.addItem(streamName);
        }
        comboBox2.addItem("best"); comboBox2.addItem("high"); comboBox2.addItem("medium"); comboBox2.addItem("low"); comboBox2.addItem("mobile");
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("StreamChooserMenu");
        frame.setContentPane(new StreamChooserMenu().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
