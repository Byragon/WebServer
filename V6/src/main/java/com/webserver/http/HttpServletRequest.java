package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象
 * 该类的每一个实例用于表示HTTP协议规定的客户端发送过来的一个请求内容
 * 每个请求由三个部分
 */
public class HttpServletRequest {
    private String method;
    private String url;
    private String protocol;
    private Map<String ,String> headers=new HashMap<>();
    private Socket socket;



    /**
     * 构造方法
     * 实例化请求对象的过程也是解析的过程
     */
    public HttpServletRequest(Socket socket) throws IOException {
        this.socket=socket;
        //解析
        parseRequestLine();
        parseHeadlers();
        parseContent();
    }

    /**
     * 解析请求行
     */
    private void parseRequestLine() throws IOException {
        //1.1解析请求行
        String line=readLine();
        System.out.println(line);
        String[] strings=line.split("\\s");
        method=strings[0];
        url=strings[1];
        protocol=strings[2];
        System.out.println("method:"+strings[0]);
        System.out.println("url:"+strings[1]);
        System.out.println("protocol:"+strings[2]);
    }




    /**
     * 解析消息头
     */
    private void parseHeadlers() throws IOException {
        //解析消息行
        String line;
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
    }

    /**
     * 解析消息正文
     */
    private void parseContent(){}

    /**
     * 读取客户端请求消息，通过socket+流
     * @return
     * @throws IOException
     */
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


    /**
     * GET方法
     * @return
     */
    public String getMethod() {
        return method;
    }
    public String getUrl() {
        return url;
    }
    public String getProtocol() {
        return protocol;
    }

    /**
     * 根据消息头的名字来返回对应的值，并不把整个map传递过去
     */
    public String getHeader(String name){
        return headers.get(name);
    }


}
