package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

/**
 * Created by Mahmood on 9/11/2014.
 */
public class SimpleUdpRPC {
    private DatagramSocket socket;
    private int bufferSize=1024;

    public SimpleUdpRPC(DatagramSocket socket)
    {
        this.setSocket(socket);
    }

    public SimpleUdpRPC(DatagramSocket socket, int bufferSize)
    {
        this.setSocket(socket);
        this.setBufferSize(bufferSize);
    }

    public byte[] Serialize(Payload payload) throws IOException {
        byte[] msgByte=payload.Serialize();
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byteArrayOutputStream.write(sizeOfMsgByte);
        byteArrayOutputStream.write(msgByte);
        byte[] dataToSend=byteArrayOutputStream.toByteArray();

        return dataToSend;
    }

    public void Send(Payload payload,InetAddress address,int port) throws IOException {
        byte[] dataToSend= this.Serialize(payload);
        byte[] data=ByteBuffer.allocate(this.bufferSize).put(dataToSend).array();
        DatagramPacket outPacket=new DatagramPacket(data,data.length,address,port);
        this.socket.send(outPacket);
    }

    public UdpPayloadBinary ReceiveBinary() throws IOException, ParseException, ClassNotFoundException {
        byte[] data=new byte[this.bufferSize];
        DatagramPacket inPacket=new DatagramPacket(data,data.length);
        socket.receive(inPacket);


        byte[] sizeofMsgByte=new byte[4];
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
        DataInputStream dataInputStream=new DataInputStream(byteArrayInputStream);
        dataInputStream.read(sizeofMsgByte);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();

        byte[] realData=new byte[sizeOfMsg];
        dataInputStream.read(realData);

        PayloadBinary payload= Payload.DeserializeFromBinary(realData);
        UdpPayloadBinary payloadToReturn=UdpPayloadBinary.Create(payload);
        payloadToReturn.setDatagramPacket(inPacket);

        return payloadToReturn;
    }

    public UdpPayloadJSON ReceiveJSON() throws IOException, ParseException {
        byte[] data=new byte[this.bufferSize];
        DatagramPacket inPacket=new DatagramPacket(data,data.length);
        socket.receive(inPacket);


        byte[] sizeofMsgByte=new byte[4];
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
        DataInputStream dataInputStream=new DataInputStream(byteArrayInputStream);
        dataInputStream.read(sizeofMsgByte);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();

        byte[] realData=new byte[sizeOfMsg];
        dataInputStream.read(realData);

        PayloadJSON payload= Payload.DeserializeFromJSON(realData);
        UdpPayloadJSON payloadToReturn=UdpPayloadJSON.Create(payload);
        payloadToReturn.setDatagramPacket(inPacket);

        return payloadToReturn;
    }

    public void setSOTimeout(int timeout) throws SocketException {
        this.socket.setSoTimeout(timeout);
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

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
