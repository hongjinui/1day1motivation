package com.naple.android.one_day_one_motivation.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naple.android.one_day_one_motivation.model.VideoDTO;

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

            ObjectMapper mapper = new ObjectMapper();
            
            //rest api response
            String result = stringBuilder.toString().trim();
            
            // 객체로 만들어서 리스트형태로 리턴
            return mapper.readValue(result, new TypeReference<ArrayList<VideoDTO>>(){});


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