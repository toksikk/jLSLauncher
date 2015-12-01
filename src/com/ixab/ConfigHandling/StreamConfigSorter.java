package com.ixab.ConfigHandling;

import java.util.ArrayList;

public class StreamConfigSorter {
    public static ArrayList<StreamConfigItem> sortStreamItemArrayList(ArrayList<StreamConfigItem> list) {
        switch (ConfigFileInstanceHandler.getConfig().getSortStreamsBy()) {
            case 1: return sortByStatus(list);
            case 2: return sortByViewerCountAsc(list);
            case 3: return sortByViewerCountDesc(list);
            case 4: return sortByNameAsc(list);
            default: return sortByStatus(list);
        }
    }

    private static ArrayList<StreamConfigItem> sortByViewerCountAsc(ArrayList<StreamConfigItem> list) {
        // TODO: implement
        return null;
    }

    private static ArrayList<StreamConfigItem> sortByViewerCountDesc(ArrayList<StreamConfigItem> list) {
        // TODO: implement
        return null;
    }

    private static ArrayList<StreamConfigItem> sortByNameAsc(ArrayList<StreamConfigItem> list) {
        // TODO: implement
        return null;
    }

    private static ArrayList<StreamConfigItem> sortByStatus(ArrayList<StreamConfigItem> list) {
        ArrayList<StreamConfigItem> result = new ArrayList<StreamConfigItem>();
        for (int i = 0; i<list.size(); i++) {
            if (list.get(i).getStatus()) {
                result.add(list.get(i));
            }
        }
        for (int i = 0; i<list.size(); i++) {
            if (!list.get(i).getStatus()) {
                result.add(list.get(i));
            }
        }
        return result;
    }
}
