package com.naple.android.one_day_one_motivation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class VideoDAO {

//    private String keyword;

//    public VideoDAO(String keyword){
//        this.keyword = keyword;
//    }
//
    public String getVideoList(String keyword){

        String serverURL = "http://napl.asuscomm.com:9998/api/"+keyword;

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

            return stringBuilder.toString().trim();


        }catch (Exception e){

            e.printStackTrace();
            return null;
        }

    }

}

/*

public class VideoDAO extends AsyncTask<String, Void, String>{

    private static final String TAG = "PHPTEST";

    public String getmJsonString() {
        return mJsonString;
    }

    private String mJsonString;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d(TAG, "response - " + result);
        if (result != null){
            mJsonString = result;
            System.out.println("@@@@@@@@@ resultlist @@@@@@"+mJsonString);
        }else{
            System.out.println("@@@@@@@@@@@@@@@@result :"+ result);
        }
    }

    @Override
    protected String doInBackground(String... params) {


        String keyword = params[0];

        String serverURL = "http://napl.asuscomm.com:9998/api/"+keyword;

        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "response code - "+ responseStatusCode);

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

            return stringBuilder.toString().trim();


        }catch (Exception e){

            Log.d(TAG, "SelectData ERROR - ", e);
            e.printStackTrace();
            return null;
        }

    }


}
*/
