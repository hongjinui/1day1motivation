package com.naple.android.one_day_one_motivation;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;

public class VideoDAO extends AsyncTask<String, Void, String>{

    private static final String TAG = "PHPTEST";

    public String getmJsonString() {
        return mJsonString;
    }

    private String mJsonString;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        System.out.println("@@@@@@@@@@@@@@@@mJsonString :"+ mJsonString);
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

        String serverURL = "C:\\Users\\houag\\AndroidStudioProjects\\select.php";
        String postParameters = "keyword="+keyword;

/*
        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "response code - "+ responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == httpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
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

 */
        return  null;
    }


}
