package com.ixab.GUI;

public class MainWindowGate {
    private static MainWindow mw;
    public static void setMainWindow(MainWindow mainWindow) {
        mw = mainWindow;
    }
    public static MainWindow getMainWindow() {
        return mw;
    }
}
