package com.nasimeshomal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by Mahmood on 9/9/2014.
 */
public class RPC {
    private Socket socket;

    public RPC(Socket socket)
    {
        this.setSocket(socket);
    }

    public byte[] Serialize(SocketData socketData) throws IOException {
        byte[] msgByte=socketData.getData();
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();
        String methodNameToSend=socketData.getMethodName();
        byte[] methodNameByte=methodNameToSend.getBytes("UTF-8");
        int sizeOfMethodName=methodNameByte.length;
        byte[] sizeOfMethodNameByte=ByteBuffer.allocate(4).putInt(sizeOfMethodName).array();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byteArrayOutputStream.write(sizeOfMethodNameByte);
        byteArrayOutputStream.write(methodNameByte);
        byteArrayOutputStream.write(sizeOfMsgByte);
        byteArrayOutputStream.write(msgByte);
        byte[] dataToSend=byteArrayOutputStream.toByteArray();

        return dataToSend;
    }

    public void Send(SocketData socketData) throws IOException {
        byte[] dataToSend= this.Serialize(socketData);
        OutputStream outputStream=this.socket.getOutputStream();
        outputStream.write(dataToSend);
        outputStream.flush();
    }

    public SocketData Receive() throws IOException {
        InputStream inputStream=this.socket.getInputStream();
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
        return new SocketData(methodName,data);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void CloseSocket() throws IOException {
        this.socket.close();
    }
}
