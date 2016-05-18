package com.ixab.ConfigHandling;

import java.io.Serializable;
import java.util.ArrayList;

public class Config implements Serializable {
    private String LSPath;
    private ArrayList<StreamConfigItem> streamItems;

    public int getSortStreamsBy() {
        return sortStreamsBy;
    }

    public void setSortStreamsBy(int sortStreamsBy) {
        this.sortStreamsBy = sortStreamsBy;
    }

    private int sortStreamsBy;

    private String proxy;

    public int getLastSelectedStream() {
        return lastSelectedStream;
    }

    public void setLastSelectedStream(int lastSelectedStream) {
        this.lastSelectedStream = lastSelectedStream;
    }

    private int lastSelectedStream;
    public int getLastSelectedQuality() {
        return lastSelectedQuality;
    }
    public void setLastSelectedQuality(int lastSelectedQuality) {
        this.lastSelectedQuality = lastSelectedQuality;
    }
    private int lastSelectedQuality;
    public Config() {
        this.streamItems = new ArrayList<StreamConfigItem>();
        setSortStreamsBy(1);
        setLastSelectedStream(-1);
        setLastSelectedQuality(-1);
    }
    public void setLSPath(String path) {
        this.LSPath = path;
        ConfigFileIOHandler.save();
    }
    public String getLSPath() {
        return this.LSPath;
    }

    public String getStreamName(int streamIndex) {
        return this.streamItems.get(streamIndex).getName();
    }
    public void addStream(String streamName) {
        this.streamItems.add(new StreamConfigItem(streamName));
        ConfigFileIOHandler.save();
    }
    public void removeStream(Object stream) {
        this.streamItems.remove(stream);
        ConfigFileIOHandler.save();
    }
    public ArrayList<StreamConfigItem> getStreams() {
        return this.streamItems;
    }
    public int getStreamCount() {
        return this.streamItems.size();
    }
    public void replaceStreamList(ArrayList<StreamConfigItem> list) {
        this.streamItems = list;
        ConfigFileIOHandler.save();
    }

    public String getProxy() {
        return proxy;
    }
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}
