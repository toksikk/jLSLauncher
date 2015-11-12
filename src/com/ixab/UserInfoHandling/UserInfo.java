package com.ixab.UserInfoHandling;

import com.ixab.GUI.ErrorMessageGate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UserInfo {
    private static JSONArray userFollowData; // TODO: change this to an arraylist of multiple jsonarrays
    private static String userFollowCount;

    private static int getFollowCount() {
        return Integer.parseInt(userFollowCount);
    }
    public static ArrayList<String> getFollowedChannelNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i<getFollowCount(); i++) {
            try {
                JSONObject followed = new JSONObject(userFollowData.get(i).toString());
                JSONObject channel = new JSONObject(followed.get("channel").toString());
                System.out.println(i+" "+channel.get("display_name").toString()); //remove this line if not needed anymore
                names.add(channel.get("display_name").toString());
                // TODO: bisher nur maximal 100 importieren wegen twitch api.
            } catch (JSONException e) {
                // workaround for stupid limit of jsonarrays of twitch's api.
                // there's nothing interesting to show.
            }
        }
        return names;
    }

    public static boolean initUserFollowData(String userName, int offset) {
        if (userName != null) {
            InputStream is = null;
            URL url = null;
            JSONObject object = null;
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://api.twitch.tv/kraken/users/");

            urlBuilder.append(userName);

            urlBuilder.append("/follows/channels?direction=DESC&limit=100&offset=");

            urlBuilder.append(offset);

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

            String result = object.get("_total").toString(); // TODO: reagiere hier auf >100, weil dann mehrere jsonarrays benötigt werden.

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
