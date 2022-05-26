package com.it.zqm.service.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @describe:
 * @author:zqm
 */
@Slf4j
@Component
public class SocketService {

    public  void server() {

        try {

            // 初始化服务端socket并且绑定9999端口
            System.out.println("等待连接");
            ServerSocket serverSocket = new ServerSocket(9999);
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            System.out.println("连接成功:" + socket.getInetAddress());

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));


            //获取输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //读取一行数据
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                //输出打印
                System.out.println(str);
                writer.write("收到！");
                writer.newLine();
                writer.flush();

            }
//            }


        } catch (IOException e) {
            log.error("socket error");

        }

    }

}
