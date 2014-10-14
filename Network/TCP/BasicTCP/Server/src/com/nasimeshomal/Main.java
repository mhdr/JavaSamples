package com.nasimeshomal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        int port=9001;
        ServerSocket serverSocket=new ServerSocket(port);

        while (true)
        {
            Socket socket= serverSocket.accept();

            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();

            byte[] recvData=new byte[1024];

            inputStream.read(recvData);
            String name=new String(recvData,"UTF-8");

            System.out.println(name);

            String msg=String.format("Hello %s",name);

            byte[] dataToSend=msg.getBytes("UTF-8");

            outputStream.write(dataToSend);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}
