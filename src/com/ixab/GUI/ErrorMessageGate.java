package com.ixab.GUI;

public class ErrorMessageGate {
    public static void setErrorText(String s) {
        MainWindow mw = MainWindowGate.getMainWindow();
        mw.getErrorLabel().setText(s);
    }
}
