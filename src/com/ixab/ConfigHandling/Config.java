package com.ixab.ConfigHandling;

import java.io.Serializable;
import java.util.ArrayList;

public class Config implements Serializable {
    private String LSPath;
    private ArrayList<String> streamNames;
    public Config() {
        this.streamNames = new ArrayList<String>();
    }
    public void setLSPath(String path) {
        this.LSPath = path;
    }
    public String getLSPath() {
        return this.LSPath;
    }

    public String getStreamName(int streamIndex) {
        return this.streamNames.get(streamIndex);
    }

    public void addStream(String streamName) {
        this.streamNames.add(streamName);
    }
    public void removeStream(Object streamName) {
        this.streamNames.remove(streamName);
    }
    public ArrayList<String> getStreams() {
        return this.streamNames;
    }
    public int getStreamCount() {
        return this.streamNames.size();
    }
}
