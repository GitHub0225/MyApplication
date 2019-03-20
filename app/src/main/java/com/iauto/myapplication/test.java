package com.iauto.myapplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class test {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        while (true){
            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            os.write("hello world".getBytes("utf-8"));
            os.close();
        }
    }
}
