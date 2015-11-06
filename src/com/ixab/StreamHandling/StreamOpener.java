package com.ixab.StreamHandling;

import com.ixab.ConfigHandling.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StreamOpener extends Thread {
    private Config c;
    private int streamIndex;
    private String quality;
    public StreamOpener(Config c, int streamIndex, String quality) {
        this.c = c;
        this.streamIndex = streamIndex;
        this.quality = quality;
    }
    public void run() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec(c.getLSPath()+" twitch.tv/"+c.getStreamName(streamIndex)+" "+quality);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
