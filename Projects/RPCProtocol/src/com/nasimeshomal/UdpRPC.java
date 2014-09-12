package com.nasimeshomal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by Mahmood on 9/11/2014.
 */
public class UdpRPC {
    private DatagramSocket socket;

    public UdpRPC(DatagramSocket socket)
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

    public void Send(SocketData socketData,InetAddress address,int port) throws IOException {
        byte[] dataToSend= this.Serialize(socketData);
        DatagramPacket outPacket=new DatagramPacket(dataToSend,dataToSend.length,address,port);
        this.socket.send(outPacket);
    }

    public SocketData Receive() throws IOException {

        byte[] sizeOfMethodNameByte=new byte[4];
        DatagramPacket inPacket=new DatagramPacket(sizeOfMethodNameByte,sizeOfMethodNameByte.length);
        socket.receive(inPacket);

        int sizeOfMethodName=ByteBuffer.wrap(sizeOfMethodNameByte).getInt();
        byte[] methodNameByte=new byte[sizeOfMethodName];
        inPacket=new DatagramPacket(methodNameByte,methodNameByte.length);
        socket.receive(inPacket);
        String methodName=new String(methodNameByte,"UTF-8");

        byte[] sizeofMsgByte=new byte[4];
        inPacket=new DatagramPacket(sizeofMsgByte,sizeofMsgByte.length);
        socket.receive(inPacket);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
        byte[] data=new byte[sizeOfMsg];
        inPacket=new DatagramPacket(data,data.length);
        socket.receive(inPacket);

        SocketData socketData=new SocketData(methodName,data);

        return socketData;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public void CloseSocket() throws IOException {
        this.socket.close();
    }
}
