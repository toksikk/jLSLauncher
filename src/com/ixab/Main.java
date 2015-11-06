package com.ixab;

import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileIOHandler;
import com.ixab.ConfigHandling.ConfigFileInstanceHandler;
import com.ixab.GUI.StreamChooserMenu;

public class Main {
    private static Config c = null;
    public static void main(String[] args) {
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
}
