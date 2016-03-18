package com.ixab.StreamHandling;

import com.ixab.ConfigHandling.Config;
import com.ixab.GUI.MainWindow;
import com.ixab.Logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StreamOpener extends Thread {
    private Config c;
    private String streamName;
    private String quality;
    private String proxy;
    private String proxyParam;
    public StreamOpener(Config c, int streamIndex, String quality) {
        this.c = c;
        this.streamName = c.getStreamName(streamIndex);
        this.quality = quality;
        this.proxy = c.getProxy();
    }
    public void run() {
        try {
            Logger.print("Starting stream livestreamer for "+this.streamName+" at "+this.quality+" quality");
            Runtime rt = Runtime.getRuntime();
            // TODO: all this needs clean up
            if (!this.proxy.equals("")) {
                StringBuilder sb = new StringBuilder();
                sb.append(" --http-proxy ");
                sb.append(this.proxy);
                this.proxyParam = sb.toString();
            } else {
                this.proxyParam = "";
            }
            Process pr = rt.exec(this.c.getLSPath()+" twitch.tv/"+this.streamName+" "+this.quality+this.proxyParam);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) {
                Logger.print(line, this);
                // TODO: analyze livestreamer output and inform gui on errors
            }

            int exitVal = pr.waitFor();
            Logger.print("Exited with error code " + exitVal, this);

        } catch(Exception e) {
            Logger.print(e.toString(), this);
            e.printStackTrace();
        }
    }
}
