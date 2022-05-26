package com.it.zqm.service.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * @describe:
 * @author:zqm
 */
@Slf4j
public class SocketClient {

    public static void client() {

        try {

            Socket socket = new Socket("127.0.0.1", 9999);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            for (int i = 1; i < 10; i++) {
                String str = "你好，这是我的第个socket"+i;

                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println(bufferedReader.readLine());
//            }

//            socket.
                //关闭socket的输出流
//            socket.shutdownOutput();
            }
            socket.shutdownInput();
            socket.close();
        } catch (IOException e) {
            log.error("socket client error");
        }

    }

    public static void main(String[] args) {
        SocketClient.client();
    }

}
