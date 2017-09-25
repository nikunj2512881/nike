package com.example.atos.myapplication.network;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

import android.os.Environment;
import android.util.*;
import android.os.AsyncTask;

import com.example.atos.myapplication.global.Constants;
import com.example.atos.myapplication.interfaces.ConnectionInterface;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;


public class RestAPIManager {


    ConnectionInterface connObj;


    public RestAPIManager(ConnectionInterface ciObj){
        this.connObj = ciObj;
    }

    public void callGetService(final String urlStr) {

        AsyncTask<Void, Void, Object> execute = new AsyncTask<Void, Void, Object>() {
            //   http://msfriend-rest-api1.apps.eu01.cf.canopy-cloud.com/getUser/test/test
            @Override
            protected Object doInBackground(Void... params) {
                HttpURLConnection urlConnection;
                StringBuilder result = null;

                try {
                    result = new StringBuilder();
                    URL url = new URL(urlStr);
                    urlConnection = (HttpURLConnection) url.openConnection();


                    int responseCode = urlConnection.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        return result;
                    } else {
                        return "\"Username/Password is incorrect!\"";

                    }


                } catch (Exception e) {

                    Log.d("myTag", "This is my message");

                }
                Log.d("myresult", "Result : " + result);
                return result;
            }

            @Override
            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                if (result instanceof String) {
                    String response = (String) result;
                if (response == "") {
                    connObj.failedWithErrorMess(response);
                } else {
                    connObj.successWithResponse(result);
                }
            }else{

                connObj.successWithResponse(result);
            }}
        }.execute();

    }

    public void callPostService(final String urlPostService,final JSONObject postDataParams) {

        new AsyncTask<Void, Void, Object>() {
            //   http://msfriend-rest-api1.apps.eu01.cf.canopy-cloud.com/getUser/test/test
            @Override
            protected Object doInBackground(Void... params) {
                HttpURLConnection urlConnection;
                StringBuilder result = null;

                try {
                    URL url = new URL(urlPostService); // here is your URL path



                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type","application/json");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(postDataParams.toString());

                    writer.flush();
                    writer.close();
                    os.close();

                    int responseCode=conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {

                        BufferedReader in=new BufferedReader(new
                                InputStreamReader(
                                conn.getInputStream()));

                        StringBuffer sb = new StringBuffer("");
                        String line="";

                        while((line = in.readLine()) != null) {

                            sb.append(line);
                            break;
                        }

                        in.close();
                        return sb.toString();

                    }
                    else {
                        return new String("false : "+responseCode);
                    }

                } catch (Exception e) {  connObj.successWithResponse(result);
                    Log.d("myTag", "This is my message");
                }
                Log.d("myresult","Result : "+result);
                return result;
            }

            @Override
            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                connObj.successWithResponse(result);
            }
        }.execute();

    }
    public void performDownloadingFromUrl(final  String urlstr, final File directory) {
        final int  MEGABYTE = 1024 * 1024;
        AsyncTask<Void, Void, Object> execute = new AsyncTask<Void, Void, Object>() {
            //   http://msfriend-rest-api1.apps.eu01.cf.canopy-cloud.com/getUser/test/test
            @Override
            protected Object doInBackground(Void... params) {
                ; HttpURLConnection urlConnection;
                StringBuilder result = null;

                try {



                    URL url = new URL(urlstr);
                     urlConnection = (HttpURLConnection)url.openConnection();
                    //urlConnection.setRequestMethod("GET");
                    //urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(directory);
                    int totalSize = urlConnection.getContentLength();

                    byte[] buffer = new byte[MEGABYTE];
                    int bufferLength = 0;
                    while((bufferLength = inputStream.read(buffer))>0 ){
                        fileOutputStream.write(buffer, 0, bufferLength);
                    }
                    fileOutputStream.close();
                } catch (Exception e) {
                   return "error";
                }
                Log.d("myresult", "Result : " + result);
                return "sucess";
            }

            @Override
            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                connObj.successWithResponse(result);
            }
        }.execute();
    }


    public void performUploadingToUrl(final  String urlstr) {


    }

    public String multipartRequest(String urlTo, Map<String, String> parmas, String filepath, String filefield, String fileMimeType)  {
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result = "";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        String[] q = filepath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);

            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("RestAPIManager", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield + "\"; filename=\"" + q[idx] + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + fileMimeType + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);

            // Upload POST Data
            Iterator<String> keys = parmas.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = parmas.get(key);

                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(value);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


            if (200 != connection.getResponseCode()) {
                //throw new CustomException("Failed to upload code:" + connection.getResponseCode() + " " + connection.getResponseMessage());
            }

            inputStream = connection.getInputStream();

            result = this.convertStreamToString(inputStream);

            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

            return result;
        } catch (Exception e) {

        }
        return "";

    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
