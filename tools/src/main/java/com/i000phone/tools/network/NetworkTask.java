package com.i000phone.tools.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/1.
 */
public class NetworkTask<T> extends AsyncTask<NetworkTask.Callback<T>,Void,Object> {
    private static Gson gson;
    static {
        gson = new GsonBuilder().setVersion(2).registerTypeAdapter(String[].class, new TypeAdapter<String[]>() {
            @Override
            public void write(JsonWriter out, String[] value) throws IOException {
                if (value != null && value.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (String s :
                            value) {
                        sb.append(s).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    out.value(sb.toString());
                } else {
                    out.nullValue();
                }
            }

            @Override
            public String[] read(JsonReader in) throws IOException {
                String s = in.nextString();
                String[] result = null;
                if (s!=null) {
                    result = s.split(",");
                }
                return result;
            }
        }).create();
    }

    public Callback<T> callback;
    public String url;
    public Type type;
    public NetworkTask(String url,Type type) {
        this.url = url;
        this.type = type;
    }

    @Override
    protected Object doInBackground(Callback<T>... params) {
        callback = params[0];
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() ==200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[10<<10];
                int len ;
                while ((len = is.read(b))!=-1){
                    bos.write(b,0,len);
                }
//                return JSON.parseObject(bos.toString(),Response.class);
                return gson.fromJson(bos.toString(),type);
            }else{
                return new Exception("ResponseCode"+conn.getResponseCode());
            }
        } catch (IOException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object response) {
        super.onPostExecute(response);
        if (response instanceof Exception) {
            callback.doFailure((Exception) response);
        }else {
            callback.doResponse((T) response);
        }
    }
    public interface Callback<T>{
        void doResponse(T t);
        void doFailure(Exception e);
    }
}
