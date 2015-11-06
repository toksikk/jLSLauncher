package com.ixab;

import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.GUI.StreamChooserMenu;

import javax.swing.*;

public class Main {
    private final static double version = 0.1;
    private static Config c = null;
    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        //ConfigLoadMenu.main(null);
        c = ConfigFileIOHandler.load();
        if (c == null) {
            initNewConfig();
            c = ConfigFileIOHandler.load();
        }
        ConfigFileInstanceHandler.setConfig(c);
        StreamChooserMenu.main(null);
    }
    public static void initNewConfig() {
        c = new Config();
        c.setLSPath("e:\\downloads\\livestreamer-v1.11.1\\livestreamer.exe");
        ConfigFileIOHandler.save(c);
    }

    public static double getVersion() {
        return version;
    }
}
