package com.ixab.ConfigHandling;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.ixab.Logging.Logger;

import java.io.*;
import java.util.Scanner;

public class ConfigFileIOHandler {
    public static Config load(String path) {

        String content;
        StringBuilder sb = new StringBuilder();
        try {
            String line = null;
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        content = sb.toString();
        Config c = (Config) JsonReader.jsonToJava(content);
        if (c instanceof Config) {
            Logger.print("Config loaded.");
            return c;
        } else {
            return null;
        }
    }
    public static void save(Config c, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(JsonWriter.formatJson(JsonWriter.objectToJson(c)));
            bw.close();
            Logger.print("Config saved.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Config load() {
        return load("config.json");
    }
    public static void save(Config c) {
        save(c, "config.json");
    }
    public static void save() {
        save(ConfigFileInstanceHandler.getConfig(), "config.json");
    }
}
