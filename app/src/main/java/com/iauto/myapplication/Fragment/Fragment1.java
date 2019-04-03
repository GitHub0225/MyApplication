package com.iauto.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.iauto.myapplication.R;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Fragment1 extends Fragment {
    //创建Fragment默认显示北京的信息。
    private String content = "北京";
    private HandlerThread mSendHandlerThread;
    private Handler mSendHander;
    private View view;
    private String string;
    private String[] parts;
    private BufferedReader bufferedReader;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private int flag;
    private Socket socket;

    @SuppressLint("ValidFragment")
    public Fragment1(String content) {
        this.content = content;
    }

    public Fragment1() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout1, container, false);
        textView1 = view.findViewById(R.id.dizhi);
        textView2 = view.findViewById(R.id.jianjie);
        textView3 = view.findViewById(R.id.fengwei);
        textView4 = view.findViewById(R.id.shijian);
        textView5 = view.findViewById(R.id.koubei);

        mSendHandlerThread = new HandlerThread("send");
        mSendHandlerThread.start();
        mSendHander = new Handler(mSendHandlerThread.getLooper(), new SendMessageCallback());

        mSendHander.sendEmptyMessage(0);



        while (flag == 0){
            try {
                System.out.println("waiting");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        textView1.setText(parts[0]);
        textView2.setText(parts[1]);
        textView3.setText(parts[2]);
        textView4.setText(parts[3]);
        textView5.setText(parts[4]);
        flag = 0;
        return view;



    }


    private class SendMessageCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message message) {

                try{
                    socket = new Socket("101.132.176.85", 30000);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes("utf-8"));
                    OutputStream outputStream=socket.getOutputStream();
                    byte[] buf=new byte[1024];
                    int len;
                    while((len=byteArrayInputStream.read(buf))!=-1){
                        outputStream.write(buf, 0, len);
                    }
                    //刷新一下缓冲区的数据
                    outputStream.flush();
                    //告诉服务器，我的数据已经发送完了
                    socket.shutdownOutput();

                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    string = bufferedReader.readLine();
                    parts = string.split("_");
                    flag = 1;
                    System.out.println(content+"-----------------------------");
                }catch (Exception e){
                    e.printStackTrace();
                }


            return false;
        }
    }
}