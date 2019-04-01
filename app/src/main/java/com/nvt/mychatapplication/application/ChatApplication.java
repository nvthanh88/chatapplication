package com.nvt.mychatapplication.application;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class ChatApplication extends Application {
    private Socket mSocket;
    private static final String URL = "http://34.92.14.1:8080";
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mSocket = IO.socket(URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public Socket getmSocket(){
        return mSocket;
    }

}
