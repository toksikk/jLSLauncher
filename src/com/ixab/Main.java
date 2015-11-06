package com.ixab;

import com.ixab.ConfigHandling.Config;
import com.ixab.ConfigHandling.ConfigFileHandler;
import com.ixab.StreamHandling.StreamOpener;

public class Main {
    private static boolean running = true;
    public static void exitProgram() {
        running = false;
    }
    public static void main(String[] args) {
        while (running) {
            // TODO: config dialog
            // TODO: stream choosing
        }
        // begin test
        //Config c = new Config();
        Config c = ConfigFileHandler.load();
        //c.addStream("cohhcarnage");
        //c.setLSPath("e:\\downloads\\livestreamer-v1.11.1\\livestreamer.exe");
        //ConfigFileHandler.save(c);

        StreamOpener so = new StreamOpener(c, 1, "best");
        so.start();
        // end test
    }
}
