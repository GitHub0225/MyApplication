package com.iauto.myapplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//192.168.48.156
public class ServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30000);
        int i = 0;
        while (true){
            i++;
            String j = i+"";
            Socket socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            if(socket.getInputStream()!= null){
                outputStream.write(("上海"+j+"_上海是国际性的大都市_东方明珠_每年的五六月份_口碑一流").getBytes("utf-8"));
            }else {
                outputStream.write("北京_北京是中国的首都_北京烤鸭_每年的三四月份_口碑一流".getBytes("utf-8"));
            }outputStream.close();
            socket.close();
        }
    }

}

