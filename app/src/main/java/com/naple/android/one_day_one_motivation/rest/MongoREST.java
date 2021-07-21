package com.naple.android.one_day_one_motivation.rest;

import com.naple.android.one_day_one_motivation.model.VideoDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MongoREST {

    public ArrayList<VideoDTO> getVideoList(String keyword){

        String serverURL = "http://napl.asuscomm.com/video?keyword=" + keyword;

        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseStatusCode = httpURLConnection.getResponseCode();

            InputStream inputStream;
            if(responseStatusCode == httpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while( (line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

            bufferedReader.close();

            //rest api response
            String result = stringBuilder.toString().trim();

            ArrayList<VideoDTO> arrayList = new ArrayList<>();
            VideoDTO videoDTO = null;

            JSONArray jsonArray = new JSONArray(result);
            for(int i=0; i<jsonArray.length(); i++){
                videoDTO = new VideoDTO();
                JSONObject object = (JSONObject)jsonArray.get(i);

                videoDTO.setTitle(object.get("title").toString());
                videoDTO.setUrl(object.get("url").toString());
                videoDTO.setChannelTitle(object.get("channelTitle").toString());
                videoDTO.setDuration(object.get("duration").toString());
                videoDTO.setViewCount(object.get("viewCount").toString());
                videoDTO.setDate(object.get("date").toString());
                videoDTO.setId(object.get("id").toString());
                videoDTO.setValue(object.get("value").toString());

                arrayList.add(videoDTO);

            }

            return arrayList;

        }catch (Exception e){

            e.printStackTrace();
            return null;
        }

    }

    public void loginInsertOrUpdate(String uuid) {

        String serverURL = "http://napl.asuscomm.com/user?uuid=" + uuid;

        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseStatusCode = httpURLConnection.getResponseCode();

            InputStream inputStream;
            if(responseStatusCode == httpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                inputStream = httpURLConnection.getErrorStream();
            }

        }catch (Exception e){

            e.printStackTrace();
        }

    }
}