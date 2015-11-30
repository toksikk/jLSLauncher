package com.ixab.ConfigHandling;

import com.ixab.Logging.Logger;

import java.io.*;

public class ConfigFileIOHandler {
    public static Config load(String path) {
        Config c = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            c = (Config) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException e)
        {
            Logger.print("Config class not found");
            e.printStackTrace();
            return null;
        }
        return c;
    }
    public static void save(Config c, String path) {
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream("config.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(c);
            out.close();
            fileOut.close();
            Logger.print("Config data is saved in config.dat");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    public static Config load() {
        return load("config.dat");
    }
    public static void save(Config c) {
        save(c, "config.dat");
    }
    public static void save() {
        save(ConfigFileInstanceHandler.getConfig(), "config.dat");
    }
}
