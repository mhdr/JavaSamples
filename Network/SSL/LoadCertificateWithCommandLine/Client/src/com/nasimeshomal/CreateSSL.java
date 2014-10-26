package com.nasimeshomal;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by Mahmood on 10/26/2014.
 */
public class CreateSSL {
    public SSLSocketFactory getDefault() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, KeyManagementException {
        char[] password="1234567890".toCharArray();
        String resource="resources/key";
        InputStream resourceStream= Thread.currentThread().getClass().getResourceAsStream(resource);
        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(resourceStream,password);

        TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext=SSLContext.getInstance("SSL");
        sslContext.init(null,trustManagerFactory.getTrustManagers(),null);

        SSLSocketFactory sslSocketFactory= sslContext.getSocketFactory();

        return sslSocketFactory;
    }
}
