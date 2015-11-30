package com.ixab.ConfigHandling;

import java.io.Serializable;
import java.util.ArrayList;

public class Config implements Serializable {
    private String LSPath;
    private ArrayList<StreamConfigItem> streamItems;
    public Config() {
        this.streamItems = new ArrayList<StreamConfigItem>();
    }
    public void setLSPath(String path) {
        this.LSPath = path;
    }
    public String getLSPath() {
        return this.LSPath;
    }

    public String getStreamName(int streamIndex) {
        return this.streamItems.get(streamIndex).getName();
    }
    public void addStream(String streamName) {
        this.streamItems.add(new StreamConfigItem(streamName));
    }
    public void removeStream(Object stream) {
        this.streamItems.remove(stream);
    }
    public ArrayList<StreamConfigItem> getStreams() {
        return this.streamItems;
    }
    public int getStreamCount() {
        return this.streamItems.size();
    }
    public void replaceStreamList(ArrayList<StreamConfigItem> list) {
        this.streamItems = list;
    }
}
