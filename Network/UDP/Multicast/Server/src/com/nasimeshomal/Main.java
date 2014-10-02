package com.nasimeshomal;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port=9010;
        InetAddress multicastAddress=InetAddress.getByName("224.1.0.0");
        MulticastSocket socket=new MulticastSocket(port);
        socket.setTimeToLive(1);

        int counter=0;
        byte[] dataToSend= ByteBuffer.allocate(4).putInt(counter).array();
        DatagramPacket packet=new DatagramPacket(dataToSend,dataToSend.length,multicastAddress,port);

        while (true)
        {
            dataToSend=ByteBuffer.allocate(4).putInt(counter).array();
            packet.setData(dataToSend);
            socket.send(packet);
            String msg=String.format("%d",counter);
            System.out.println(msg);

            counter++;
            Thread.sleep(5*1000);
        }
    }
}
