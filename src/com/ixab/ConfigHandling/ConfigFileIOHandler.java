package com.ixab.ConfigHandling;

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
            System.out.println("Config class not found");
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
            System.out.println("Config data is saved in config.dat");
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
}
