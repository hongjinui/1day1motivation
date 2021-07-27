package com.napl.android.motivationPerDay.rest;

import com.napl.android.motivationPerDay.model.VideoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MongoREST {

    public List<VideoDTO> getVideoList(String keyword){

        // 레트로핏 동기

        List<VideoDTO> arrayList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://napl.asuscomm.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit2Service retrofit2Service = retrofit.create(Retrofit2Service.class);
        try {

            if(retrofit2Service.getData(keyword).execute().isSuccessful()){

                arrayList  = retrofit2Service.getData(keyword).execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
/*

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
*/


    }

    public void loginInsertOrUpdate(String uuid) {

//        레트로핏 비동기
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://napl.asuscomm.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofit2Service retrofit2Service = retrofit.create(Retrofit2Service.class);
        retrofit2Service.getUser(uuid).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()){
                    Map<String,String> map = response.body(); // 성공 시 stateCode : 200, msg : success
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
/*

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
*/

    }
}