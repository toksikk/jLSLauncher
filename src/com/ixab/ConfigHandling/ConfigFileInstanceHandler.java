package com.ixab.ConfigHandling;

public class ConfigFileInstanceHandler {
    public static Config getConfig() {
        return config;
    }

    public static void setConfig(Config c) {
        config = c;
    }

    private static Config config;
}
