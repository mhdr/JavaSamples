package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

/**
 * Created by Mahmood on 9/11/2014.
 */
public class UdpRPC {
    private DatagramSocket socket;
    private int bufferSize=1024;

    public UdpRPC(DatagramSocket socket)
    {
        this.setSocket(socket);
    }

    public UdpRPC(DatagramSocket socket,int bufferSize)
    {
        this.setSocket(socket);
        this.setBufferSize(bufferSize);
    }

    public void Send(Payload payload,InetAddress address,int port) throws IOException {
        int headerLength=4+4+4+4;
        byte[] msgByte=payload.Serialize();
        int sizeOfMsg=msgByte.length;

        int callerSequence=CallerSequence.getNextId();
        byte[] callerSequenceByte=ByteBuffer.allocate(4).putInt(callerSequence).array();

        int packetCapacity=this.bufferSize-headerLength;
        int answer=sizeOfMsg/packetCapacity;
        int remainder=sizeOfMsg % packetCapacity;

        int lastFragmentNumber=0;
        if (remainder==0)
        {
            lastFragmentNumber=answer;
        }
        else
        {
            lastFragmentNumber=answer+1;
        }

        int currentFragmentNumber=1;
        byte[] lastFragmentNumberByte=ByteBuffer.allocate(4).putInt(lastFragmentNumber).array();

        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(msgByte);
        DataInputStream dataInputStream=new DataInputStream(byteArrayInputStream);

        while (lastFragmentNumber>=currentFragmentNumber)
        {
            int lengthOfCurrentData;

            if (remainder!=0)
            {
                if (lastFragmentNumber==currentFragmentNumber)
                {
                    lengthOfCurrentData=remainder;
                }
                else
                {
                    lengthOfCurrentData=packetCapacity;
                }
            }
            else
            {
                lengthOfCurrentData=packetCapacity;
            }

            byte[] dataToSend=new byte[lengthOfCurrentData];
            dataInputStream.read(dataToSend);

            int sizeOfCurrentMsg=dataToSend.length;
            byte[] sizeOfCurrentMsgByte= ByteBuffer.allocate(4).putInt(sizeOfCurrentMsg).array();

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byteArrayOutputStream.write(sizeOfCurrentMsgByte);
            byteArrayOutputStream.write(callerSequenceByte);

            byte[] currentFragmentNumberByte=ByteBuffer.allocate(4).putInt(currentFragmentNumber).array();
            byteArrayOutputStream.write(currentFragmentNumberByte);
            byteArrayOutputStream.write(lastFragmentNumberByte);
            byteArrayOutputStream.write(dataToSend);

            byte[] wholePacketInByte=byteArrayOutputStream.toByteArray();

            byte[] data=ByteBuffer.allocate(this.bufferSize).put(wholePacketInByte).array();
            DatagramPacket outPacket=new DatagramPacket(data,data.length,address,port);
            this.socket.send(outPacket);

            currentFragmentNumber++;
        }
    }

    public UdpPacket Receive() throws IOException, ParseException {
        byte[] data=new byte[this.bufferSize];
        DatagramPacket inPacket=new DatagramPacket(data,data.length);
        socket.receive(inPacket);

        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
        DataInputStream dataInputStream=new DataInputStream(byteArrayInputStream);

        byte[] sizeofMsgByte=new byte[4];
        dataInputStream.read(sizeofMsgByte);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();

        byte[] callerSequenceByte=new byte[4];
        dataInputStream.read(callerSequenceByte);
        int callerSequence=ByteBuffer.wrap(callerSequenceByte).getInt();

        byte[] fragmentNumberByte=new byte[4];
        dataInputStream.read(fragmentNumberByte);
        int fragmentNumber=ByteBuffer.wrap(fragmentNumberByte).getInt();

        byte[] lastFragmentNumberByte=new byte[4];
        dataInputStream.read(lastFragmentNumberByte);
        int lastFragmentNumber=ByteBuffer.wrap(lastFragmentNumberByte).getInt();

        byte[] realData=new byte[sizeOfMsg];
        dataInputStream.read(realData);

        UdpPacket udpPacket=new UdpPacket(inPacket.getPort(),inPacket.getAddress(),callerSequence,fragmentNumber,lastFragmentNumber,realData);

        return udpPacket;
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
