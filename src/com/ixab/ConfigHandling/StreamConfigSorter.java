package com.ixab.ConfigHandling;

import java.util.ArrayList;

public class StreamConfigSorter {
    public static ArrayList<StreamConfigItem> sortStreamItemArrayList(ArrayList<StreamConfigItem> list) {
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
