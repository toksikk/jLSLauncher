package com.ixab.UserInfoHandling;

import com.ixab.GUI.ErrorMessageGate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UserInfo {
    private static JSONArray userFollowData;
    private static String userFollowCount;

    private static int getFollowCount() {
        return Integer.getInteger(userFollowCount);
    }
    public static ArrayList<String> getFollowedChannelNames() {
        ArrayList<String> names = new ArrayList<String>();
        System.out.println(userFollowData.toString()); // TODO: implement something to work with
        return names;
    }

    public static boolean initUserFollowData(String userName) {
        if (userName != null) {
            InputStream is = null;
            URL url = null;
            JSONObject object = null;
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://api.twitch.tv/kraken/users/");

            urlBuilder.append(userName);

            urlBuilder.append("/follows/channels");

            String URLString = urlBuilder.toString();

            try {
                url = new URL(URLString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                is = url.openStream();
            } catch (FileNotFoundException e) {
                ErrorMessageGate.setErrorText("Benutzer "+userName+" existiert nicht.");
                e.printStackTrace();
                return false;
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

            String result = object.get("_total").toString();

            if (result == "null") {
                userFollowData = null;
                userFollowCount = null;
            } else {
                userFollowData = new JSONArray(object.getJSONArray("follows").toString());
                userFollowCount = result;
            }
        }
        return true;
    }
}
