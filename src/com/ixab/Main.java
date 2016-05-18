package com.ixab;

import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.GUI.AlertWindow;
import com.ixab.GUI.MainWindow;
import com.ixab.Logging.Logger;

import javax.swing.*;

public class Main {
    private final static String version = "0.55 Alpha";
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
        c = ConfigFileIOHandler.load();
        if (c == null) {
            initNewConfig();
            c = ConfigFileIOHandler.load();
        }
        ConfigFileInstanceHandler.setConfig(c);
        MainWindow.main(null);
    }
    public static void initNewConfig() {
        c = new Config();
        JFrame jf = new JFrame();
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Livestreamer Programmdatei auswählen");
        jfc.showOpenDialog(jf);
        if (jfc.getSelectedFile() == null) System.exit(-1);
        c.setLSPath(jfc.getSelectedFile().getAbsolutePath());
        c.setProxy("");
        jf.dispose();
        ConfigFileIOHandler.save(c);
    }

    public static String getVersion() {
        return version;
    }
}
