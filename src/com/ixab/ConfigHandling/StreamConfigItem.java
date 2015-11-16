package com.ixab.ConfigHandling;

import com.ixab.StreamHandling.StreamInfo;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class StreamConfigItem implements Serializable {
    private String name;
    private String title;
    private boolean status;
    private String category;
    private int viewers;
    private transient BufferedImage previewImage;

    public StreamConfigItem(String name) {
        this.getInfo();
        this.name = name;
    }

    private void getInfo() {
        StreamInfo.initStreamData(this.name);
        String s = StreamInfo.getStatus();
        if (s.equals("online")) {
            this.status = true;
        } else {
            this.status = false;
        }
        this.viewers = Integer.parseInt(StreamInfo.getViewers());
        this.title = StreamInfo.getTitle();
        this.previewImage = StreamInfo.getPreviewImage();
        this.category = StreamInfo.getGame();
    }
    public void refreshInfo() {
        this.getInfo();
    }

    public String toString() {
        return this.getInfoText();
    }

    public String getInfoText() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" - ");
        sb.append(this.getStatusString());
        if (this.status) {
            //sb.append(" spielt ");
            //sb.append(this.getCategory());
            sb.append(" mit ");
            sb.append(this.getViewers());
            sb.append(" Zuschauern");
        }

        return sb.toString();
    }

    public String getName() {
        return this.name;
    }
    public int getViewers() {
        return this.viewers;
    }
    public String getCategory() {
        return this.category;
    }
    public boolean getStatus() {
        return this.status;
    }
    public String getStatusString() {
        if (status) {
            return "online";
        } else {
            return "offline";
        }
    }
    public String getTitle() {
        return this.title;
    }
    public BufferedImage getPreviewImage() {
        return this.previewImage;
    }
}
