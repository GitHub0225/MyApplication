package com.iauto.myapplication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class test {

    public static void main(String[] args) throws Exception {



//192.168.48.156

        BufferedReader bufferedReader;
        ServerSocket serverSocket = new ServerSocket(8080);
        int i = 0;
        while (true){
            i++;
            String j = i+"";
            Socket socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            // bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
            if(socket.getInputStream()!=null){
                outputStream.write(("上海"+j+"_上s海是国际性的大都市_东方明珠_每年的五六月份_口碑一流").getBytes("utf-8"));
            }else {
                outputStream.write("北京_北京是中国的首都_北京烤鸭_每年的三四月份_口碑一流".getBytes("utf-8"));
            }outputStream.close();
            socket.close();
        }
    }

}
