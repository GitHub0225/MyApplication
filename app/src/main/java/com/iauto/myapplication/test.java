package com.iauto.myapplication;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class test {

    public static void main(String[] args) throws IOException {

        new Thread(){
            @Override
            public void run() {
                try{
                    Socket socket = new Socket("101.132.176.85",30000);
                    FileInputStream fileInputStream = new FileInputStream("/home/wangjian/Desktop/1.txt");
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream())
                    );
                    String strings = bufferedReader.readLine();
                    System.out.println(strings);
                    bufferedReader.close();
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

}

