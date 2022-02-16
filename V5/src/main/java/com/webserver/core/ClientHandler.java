package com.webserver.core;

import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 该线程负责与指定的客户端完成HTTP交互
 * 每次HTTP交互都采取一问一答的规则，因此交互由三步完成：
 * 1、解析请求
 * 2、处理请求
 * 3、发送响应
 */
public class ClientHandler implements Runnable{

    private Socket socket;
    // 构造方法
    public ClientHandler(Socket socket) {
        this.socket=socket;
    }

//    @Override
//    public void run() {
//        try {
//
//            InputStream in=socket.getInputStream();
//            int d;
//            while((d=in.read())!=-1){
//                System.out.print((char)d);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    @Override
    public  void run(){
        try {
            //解析请求行
            String line=readLine();
            System.out.println(line);
            String[] strings=line.split("\\s");
            System.out.println("method:"+strings[0]);
            System.out.println("url:"+strings[1]);
            System.out.println("protocol:"+strings[2]);

            Map<String ,String> headers=new HashMap<>();


            //解析消息行
            while(true){
                line=readLine();
                if(line.isEmpty()){
                    break;
                }
                //将消息头的名字和值以key和value的形式存入headlers这个Map中

                String[] strs=line.split(":\\s");
                headers.put(strs[0],strs[1]);

            }
            System.out.println(headers);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readLine() throws IOException {
        InputStream in=socket.getInputStream();
        StringBuilder builder=new StringBuilder();
        int d;
        char pre='a',cur='a';
        while((d=in.read())!=-1){
            cur=(char)d;
            if(pre==13 && cur==10){
                break;
            }
            builder.append(cur);
            pre=cur;
        }
        return builder.toString().trim();
    }




}
