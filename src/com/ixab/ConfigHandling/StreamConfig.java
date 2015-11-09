package com.ixab.ConfigHandling;

import com.ixab.StreamHandling.StreamInfo;

public class StreamConfig {
    private String name;

    public StreamConfig(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public String getInfo() {
        StreamInfo.initStreamData(name);
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" - ");
        String status = StreamInfo.getStatus();
        sb.append(status);
        if (!status.equals("offline")) {
            sb.append(" playing ");
            sb.append(StreamInfo.getGame());
            sb.append(" with ");
            sb.append(StreamInfo.getViewers());
            sb.append(" Viewers");
        }

        return sb.toString();
    }
}
