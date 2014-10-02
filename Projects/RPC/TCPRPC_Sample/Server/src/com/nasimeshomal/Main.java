package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        int port=9001;
        DatagramSocket socket=new DatagramSocket(port);
        UdpPacketTable udpPacketTable=UdpPacketTable.getInstance();

        Boolean endOfData=false;

        while (!endOfData)
        {
            UdpRPC udpRPC=new UdpRPC(socket);

            UdpPacket udpPacket=udpRPC.Receive();
            int remain= udpPacketTable.addPacket(udpPacket);

            if (remain==0)
            {
                endOfData=true;
            }
        }

        ArrayList<UdpPacket> packets=udpPacketTable.getPackets();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        for (UdpPacket packet:packets)
        {
            byteArrayOutputStream.write(packet.getData());
        }

        PayloadJSON payloadJSON=Payload.DeserializeFromJSON(byteArrayOutputStream.toByteArray());

        System.out.println("");
    }
}
