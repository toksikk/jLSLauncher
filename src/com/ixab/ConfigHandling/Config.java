package com.ixab.ConfigHandling;

import java.io.Serializable;
import java.util.ArrayList;

public class Config implements Serializable {
    private String LSPath;
    private ArrayList<String> streamNames;
    private ArrayList<StreamConfigItem> streamItems;
    public Config() {
        this.streamNames = new ArrayList<String>();
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
        //return this.streamNames.get(streamIndex);
    }

    public void addStream(String streamName) {
        this.streamItems.add(new StreamConfigItem(streamName));
        this.streamNames.add(streamName);
    }
    public void removeStream(Object stream) {
        this.streamItems.remove(stream);
        this.streamNames.remove(stream);
    }
    public ArrayList<StreamConfigItem> getStreams() {
    // public ArrayList<StreamConfigItem> getStreams() {
        return this.streamItems;
        // return this.streamNames;
    }
    public int getStreamCount() {
        return this.streamItems.size();
        // return this.streamNames.size();
    }
}
