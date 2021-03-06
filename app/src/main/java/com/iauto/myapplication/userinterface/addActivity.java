package com.iauto.myapplication.userinterface;
/**
 * created by {Paul}
 * on 19-4-25
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iauto.myapplication.R;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class addActivity extends Activity {
    private HandlerThread mHandlerThread;
    private Handler mhandler;
    private Socket socket;
    private String string;
    private Button submitbutton;
    private EditText adddizhi;
    private EditText addjianjie;
    private EditText addfengwei;
    private EditText addshijian;
    private EditText addkoubei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mHandlerThread = new HandlerThread("submitHandlerThread");
        mHandlerThread.start();
        mhandler = new Handler(mHandlerThread.getLooper(),new submitCallBack());

        adddizhi = (EditText)findViewById(R.id.adddizhi);
        addjianjie = (EditText)findViewById(R.id.addjianjie);
        addfengwei = (EditText)findViewById(R.id.addfengwei);
        addshijian = (EditText)findViewById(R.id.addshijian);
        addkoubei = (EditText)findViewById(R.id.addkoubei);
        submitbutton = (Button)findViewById(R.id.submit);


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] strings = new String[5] ;
                strings[0] = adddizhi.getText().toString();
                strings[1] = addjianjie.getText().toString();
                strings[2] = addfengwei.getText().toString();
                strings[3] = addshijian.getText().toString();
                strings[4] = addkoubei.getText().toString();
                if(strings[0].length()!=0&&strings[1].length()!=0&&strings[2].length()!=0&&strings[3].length()!=0&&strings[4].length()!=0){
                    string = strings[0]+"_"+strings[1]+"_"+strings[2]+"_"+strings[3]+"_"+strings[4];
                    mhandler.sendEmptyMessage(0);
                }else {
                    Toast.makeText(addActivity.this,"请将内容输入完整",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private class submitCallBack implements Handler.Callback {
        @Override
        public boolean handleMessage(Message message) {
            try{
                socket = new Socket("101.132.176.85", 30001);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string.getBytes("utf-8"));
                OutputStream outputStream=socket.getOutputStream();
                byte[] buf=new byte[1024];
                int len;
                while((len=byteArrayInputStream.read(buf))!=-1){
                    outputStream.write(buf, 0, len);
                }
                byteArrayInputStream.close();
                //刷新一下缓冲区的数据
                outputStream.flush();
                //告诉服务器，我的数据已经发送完了
                socket.shutdownOutput();
                Toast.makeText(addActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addActivity.this, MainActivity.class));
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }
    }
}