package com.example.demodbbootclient.utils;

import com.example.demodbbootclient.controller.RegisterOverviewController;
import com.example.demodbbootclient.entity.Register;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;


public class HTTPUtils {
    OkHttpClient client = new OkHttpClient();
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder().url(url).post(body).build();
            try (Response response = client.newCall(request).execute()) {
                    return response.body().string();
            }
    }

    public  String get(String url, String args) throws IOException {
        Request request = new Request.Builder().url(url + args).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String url, String json, String id) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url + "update").post(body).build();
        try (Response response = client.newCall(request).execute()) {
            //System.out.println(request.url() + "" + id);
            return response.body().string();
        }
    }


    public String delete(String url, Integer id) throws IOException  {
        Request request = new Request.Builder().url(url + "delete?id=" + id).delete().build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
