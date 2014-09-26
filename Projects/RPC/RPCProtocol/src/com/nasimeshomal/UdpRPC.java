package com.nasimeshomal;

import org.json.simple.parser.ParseException;

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

    public UdpRPC(DatagramSocket socket,int bufferSize)
    {
        this.setSocket(socket);
        // TODO bufferSize
    }

    public byte[] Serialize(Payload payload) throws IOException {
        byte[] msgByte=payload.Serialize();
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();
        String methodNameToSend=payload.getMethodName();
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

    public void Send(Payload payload,InetAddress address,int port) throws IOException {
        byte[] dataToSend= this.Serialize(payload);
        DatagramPacket outPacket=new DatagramPacket(dataToSend,dataToSend.length,address,port);
        this.socket.send(outPacket);
    }

    public PayloadBinary ReceiveBinary() throws IOException, ParseException, ClassNotFoundException {
        DatagramPacket inPacket=null;
        socket.receive(inPacket);

        byte[] sizeofMsgByte=new byte[4];
        inPacket=new DatagramPacket(sizeofMsgByte,sizeofMsgByte.length);
        socket.receive(inPacket);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
        byte[] data=new byte[sizeOfMsg];
        inPacket=new DatagramPacket(data,data.length);
        socket.receive(inPacket);

        PayloadBinary payload= Payload.DeserializeFromBinary(data);

        return payload;
    }

    public PayloadJSON ReceiveJSON() throws IOException, ParseException {

        DatagramPacket inPacket=null;
        socket.receive(inPacket);

        byte[] sizeofMsgByte=new byte[4];
        inPacket=new DatagramPacket(sizeofMsgByte,sizeofMsgByte.length);
        socket.receive(inPacket);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
        byte[] data=new byte[sizeOfMsg];
        inPacket=new DatagramPacket(data,data.length);
        socket.receive(inPacket);

        PayloadJSON payload= Payload.DeserializeFromJSON(data);

        return payload;
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
