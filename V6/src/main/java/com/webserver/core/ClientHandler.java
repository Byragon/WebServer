package com.webserver.core;

import com.webserver.http.HttpServletRequest;

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
            HttpServletRequest request = new HttpServletRequest(socket);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
