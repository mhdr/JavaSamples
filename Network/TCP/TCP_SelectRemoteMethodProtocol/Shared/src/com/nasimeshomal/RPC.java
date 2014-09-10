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
    private SocketData socketData;
    private Socket socket;

    public RPC(Socket socket)
    {
        this.setSocket(socket);
    }

    public RPC(Socket socket,SocketData socketData)
    {
        this.setSocket(socket);
        this.setSocketData(socketData);
    }

    public byte[] Serialize() throws IOException {
        byte[] msgByte=this.socketData.getData();
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();
        String methodNameToSend=this.socketData.getMethodName();
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

    public void Send() throws IOException {
        byte[] dataToSend= this.Serialize();
        OutputStream outputStream=this.socket.getOutputStream();
        outputStream.write(dataToSend);
        outputStream.flush();
        outputStream.close();
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

        inputStream.close();

        return new SocketData(methodName,data);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SocketData getSocketData() {
        return socketData;
    }

    public void setSocketData(SocketData socketData) {
        this.socketData = socketData;
    }

    public void CloseSocket() throws IOException {
        this.socket.close();
    }
}
