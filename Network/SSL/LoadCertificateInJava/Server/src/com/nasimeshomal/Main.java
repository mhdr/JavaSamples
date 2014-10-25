package com.nasimeshomal;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        SSLServerSocketFactory sslServerSocketFactory= (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket= (SSLServerSocket) sslServerSocketFactory.createServerSocket(9001);

        while (true)
        {
            SSLSocket sslSocket= (SSLSocket) sslServerSocket.accept();

            InputStream inputStream=sslSocket.getInputStream();
            OutputStream outputStream=sslSocket.getOutputStream();

            byte[] recvData=new byte[1024];

            inputStream.read(recvData);
            String recvDataStr=new String(recvData,"UTF-8");

            String response=String.format("Hello %s",recvDataStr);
            byte[] responseByte=response.getBytes("UTF-8");
            outputStream.write(responseByte);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
            sslSocket.close();
        }
    }
}
