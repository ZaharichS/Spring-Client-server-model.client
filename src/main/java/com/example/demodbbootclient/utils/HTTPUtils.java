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

/*    public BaseResponse post(String url, Register register) throws IOException {
        System.out.println(url);
        RequestBody body = RequestBody.create(
                gson.toJson(register),
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isRedirect()) throw new IOException("Unexpected code" + response);
            return gson.fromJson(response.body().toString(), BaseResponse.class);
        }*/
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

    public String put(String url, Integer id, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url + "" + id).put(body).build();
        try (Response response = client.newCall(request).execute()) {
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
