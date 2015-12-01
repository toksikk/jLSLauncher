package com.ixab.GUI;

import com.ixab.Logging.Logger;

public class ErrorMessageGate {
    public static void setErrorText(String s) {
        Logger.print(s);
        MainWindow mw = MainWindowGate.getMainWindow();
        mw.getErrorLabel().setText(s);
    }
}
