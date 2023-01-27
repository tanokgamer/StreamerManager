package com.ash.listOfStreamer;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class listOfStreamer  extends Thread{

    public ArrayList<String> Streamer= new ArrayList<>();

    public void starts() throws ClassNotFoundException, NoSuchMethodException {

        JSONObject jo = new JSONObject(
                  "{\"12\":\"\",\"13\":\"\",\"14\":\"\",\"15\":\"\",\"16\":\"\",\"17\":\"zukekito\",\"18\":\"laguaridadel_manco \",\"19\":\"\",\"20\":\"\",\"21\":\"\",\"22\":\"PANDA__ART\",\"23\":\"Kiyomi\"}"
        );
        System.out.println(jo);
        Calendar calendar = GregorianCalendar.getInstance();
        System.out.println("hora"+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTO"+calendar.get(Calendar.MINUTE));
       // listas(calendar, "");
        Iterator<String> keys = jo.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            if(jo.getString(key).length() >0) {
                System.out.println(jo.getString(key));
            }
        }

            System.out.println("dd");
    }

    public void listas(Calendar calendar, String streamer)
    {
        try {
           // System.out.println("calculo"+((calendar.get(Calendar.MINUTE) * 1)));
            if (calendar.get(Calendar.MINUTE) < 30) {
                sleep((30*60*1000) - ((calendar.get(Calendar.MINUTE) * 60 * 1000)));
            }
            apiTwitch(streamer);
            if (calendar.get(Calendar.MINUTE) < 50) {
                sleep((50*60*1000) - (calendar.get(Calendar.MINUTE) * 60 * 1000));
            }
            apiTwitch(streamer);
        }
        catch (Exception e) {
            System.err.println("Fallo en sacar lista de"+streamer);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            System.out.println(sStackTrace);
        }
    }
    public void apiTwitch(String Channel) {
        ArrayList<String> viewers =  new ArrayList<>();
        ArrayList<String> categories= new ArrayList<>();
        categories.add("vips");
        categories.add("moderators");
        categories.add("admins");
        categories.add("staff");
        categories.add("global_mods");
        categories.add("viewers");
        try {

            URL url = new URL("https://tmi.twitch.tv/group/user/" + Channel + "/chatters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            String result="";
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                result='['+output+']';
            }
          //  System.out.println("antes del for"+result);
             JSONArray array = new JSONArray(result);
          //  System.out.println("antes del for");
            JSONObject chatters = new JSONObject();
            for(int i=0; i < array.length(); i++)
            {
          //      System.out.println("despues del for");
                JSONObject object = array.getJSONObject(i);
            //    System.out.println(object.getJSONObject("chatters"));
                chatters = object.getJSONObject("chatters");
            }
        //    System.out.println("deserealizar");
          //System.out.println(chatters.getJSONArray("vips"));
         //   System.out.println(chatters.getJSONArray("viewers"));
            /*  System.out.println(chatters.getJSONArray("global_mods"));
            System.out.println(chatters.getJSONArray("staff"));
            System.out.println(chatters.getJSONArray("admins"));
            System.out.println(chatters.getJSONArray("moderators"));
*/

            /*System.out.println(chatters.getJSONArray("viewers").length());
            for (int i=0;i<chatters.getJSONArray("viewers").length();i++){
                System.out.println("value"+chatters.getJSONArray("viewers").get(i));
            }
            */
           // System.out.println("categoria"+categories.get(2));
            for (int i=0;i<6;i++){
             //   System.out.println("value"+chatters.getJSONArray(categories.get(i)));
                JSONArray r = chatters.getJSONArray(categories.get(i));
                for (int k=0;k <r.length(); k++)
                {
                    viewers.add(r.getString(k));
                }
                //t.add(String.valueOf(chatters.getJSONArray(categories.get(i))));
               // System.out.println("resultado"+t);
                //System.out.println("value"+chatters.getJSONArray(categories.get(i)));
            }
            System.out.println("Lista oficial"+viewers);
            conn.disconnect();
        }
         catch (Exception e) {
             System.err.println("Error: Fallo en sacar lista de "+Channel);
             StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw);
             e.printStackTrace(pw);
             String sStackTrace = sw.toString(); // stack trace as a string
             System.out.println(sStackTrace);
            }
    }
}