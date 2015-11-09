package com.ixab.StreamHandling;

import com.ixab.ConfigHandling.Config;
import com.ixab.GUI.MainWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StreamOpener extends Thread {
    private Config c;
    private String streamName;
    private String quality;
    public StreamOpener(Config c, int streamIndex, String quality) {
        this.c = c;
        this.streamName = c.getStreamName(streamIndex);
        this.quality = quality;
    }
    public void run() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(this.c.getLSPath()+" twitch.tv/"+this.streamName+" "+this.quality);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) {
                System.out.println(line);
                // TODO: analyze livestreamer output and inform gui on errors
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
