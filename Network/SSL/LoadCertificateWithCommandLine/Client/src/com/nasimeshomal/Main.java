package com.nasimeshomal;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class Main {

    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        InetAddress address=InetAddress.getByName("127.0.0.1");
        int port=9001;

        CreateSSL createSSL=new CreateSSL();
        SSLSocketFactory sslSocketFactory=createSSL.getDefault();
        SSLSocket sslSocket= (SSLSocket) sslSocketFactory.createSocket(address,port);
        final String[] enabledCipherSuites = { "SSL_DH_anon_WITH_RC4_128_MD5" };
        sslSocket.setEnabledCipherSuites(enabledCipherSuites);

        InputStream inputStream=sslSocket.getInputStream();
        OutputStream outputStream=sslSocket.getOutputStream();

        String name="Mahmood";
        byte[] data=name.getBytes("UTF-8");

        outputStream.write(data);
        outputStream.flush();

        byte[] recvData=new byte[1024];
        inputStream.read(recvData,0,recvData.length);

        String recvMsg=new String(recvData,"UTF-8");
        System.out.println(recvMsg);

        inputStream.close();
        outputStream.close();
        sslSocket.close();
    }
}
