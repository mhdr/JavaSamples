package com.nasimeshomal;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.CertificateException;

public class Main {

    public static void main(String[] args) throws IOException, KeyManagementException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {

        CreateSSL createSSL=new CreateSSL();
        SSLServerSocketFactory sslServerSocketFactory=createSSL.getDefault();
        SSLServerSocket sslServerSocket= (SSLServerSocket) sslServerSocketFactory.createServerSocket(9001);
        final String[] enabledCipherSuites = { "SSL_DH_anon_WITH_RC4_128_MD5" };
        sslServerSocket.setEnabledCipherSuites(enabledCipherSuites);

        while (true)
        {
            SSLSocket sslSocket= (SSLSocket) sslServerSocket.accept();

            InputStream inputStream=sslSocket.getInputStream();
            OutputStream outputStream=sslSocket.getOutputStream();

            byte[] recvData=new byte[1024];

            inputStream.read(recvData);
            String recvDataStr=new String(recvData,"UTF-8");

            System.out.println(recvDataStr);

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
