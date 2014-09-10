package com.nasimeshomal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        ServerSocket serverSocket=new ServerSocket(9001);

        while (true)
        {
            Socket newSocket= serverSocket.accept();
            InputStream inputStream=newSocket.getInputStream();
            OutputStream outputStream=newSocket.getOutputStream();

            byte[] sizeOfMethodNameByte=new byte[4];
            inputStream.read(sizeOfMethodNameByte);
            int sizeOfMethodName=ByteBuffer.wrap(sizeOfMethodNameByte).getInt();
            byte[] methodNameByte=new byte[sizeOfMethodName];
            inputStream.read(methodNameByte);
            String methodName=new String(methodNameByte,"UTF-8");

            byte[] sizeofMsgByte=new byte[4];
            inputStream.read(sizeofMsgByte);
            int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
            byte[] data=new byte[sizeOfMsg];

            inputStream.read(data);

            String msg=new String(data,"UTF-8");

            System.out.println(msg);

            Class<Greeting> aClass=Greeting.class;
            Method[] methods=aClass.getMethods();

            Method methodMatched=null;

            for (Method method:methods)
            {
                if (method.getName().equals(methodName))
                {
                    methodMatched=method;
                }
            }

            byte[] returnValue= (byte[]) methodMatched.invoke(null,data);

            RPC rpc=new RPC("",returnValue);
            byte[] dataToSend=rpc.Serialize();

            outputStream.write(dataToSend);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }
}
