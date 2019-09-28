package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String urlApi = "..";
    public TextView test;
    String nameResponse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void hola(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    URL urlService = new URL(urlApi);
                    HttpURLConnection connection = (HttpURLConnection) urlService.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream response = connection.getInputStream();

                    if(connection.getResponseCode() == 200){

                        InputStreamReader responseBody = new InputStreamReader(response);
                        JsonReader jsonRead = new JsonReader(responseBody);
                        jsonRead.beginObject();
                        String key = jsonRead.nextName();
                        String value = jsonRead.nextString();

                        nameResponse = value;

                        test.post(new Runnable() {
                            @Override
                            public void run() {
                                test.setText(nameResponse);

                            }
                        });
                    }

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
