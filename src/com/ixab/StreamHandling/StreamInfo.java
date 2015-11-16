package com.ixab.StreamHandling;

import com.ixab.GUI.ErrorMessageGate;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StreamInfo {
    private static JSONObject streamData = null;
    private static JSONObject streamPreviewData = null;
    public static String getGame() {
        if (streamData == null && streamPreviewData == null) {
            return "-";
        } else {
            JSONObject channelData = new JSONObject(streamData.get("channel").toString());
            return channelData.get("game").toString();
        }
    }
    public static String getTitle() {
        if (streamData == null && streamPreviewData == null) {
            return "-";
        } else {
            JSONObject channelData = new JSONObject(streamData.get("channel").toString());
            return channelData.get("status").toString();
        }
    }
    public static String getViewers() {
        if (streamData == null && streamPreviewData == null) {
            return "0";
        } else {
            return streamData.get("viewers").toString();
        }
    }
    public static String getStatus() {
        if (streamData == null && streamPreviewData == null) {
            return "offline";
        } else {
            return "online";
        }
    }
    public static BufferedImage getPreviewImage() {
        if (streamPreviewData == null) {
            BufferedImage c = new BufferedImage(1,1,1);
            return c;
        } else {
            URL url = null;
            BufferedImage c = null;
            try {
                url = new URL(streamPreviewData.get("medium").toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                c = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return c;
        }
    }
    public static boolean initStreamData(String streamName) {
        if (streamName != null) {
            InputStream is = null;
            URL url = null;
            JSONObject object = null;
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://api.twitch.tv/kraken/streams/");

            urlBuilder.append(streamName);

            String URLString = urlBuilder.toString();

            try {
                url = new URL(URLString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                is = url.openStream();
            } catch (IOException e) {
                ErrorMessageGate.setErrorText("Fehler bei Verbindungsaufbau zu Twitch.");
                e.printStackTrace();
                return false;
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            try {
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                object = new JSONObject(sb.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            String result = object.get("stream").toString();

            if (result == "null") {
                streamData = null;
                streamPreviewData = null;
            } else {
                streamData = new JSONObject(result);
                streamPreviewData = new JSONObject(streamData.get("preview").toString());
            }
        }
        return true;
    }
}
