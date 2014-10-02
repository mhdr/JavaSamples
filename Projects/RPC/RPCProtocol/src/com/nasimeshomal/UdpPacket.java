package com.nasimeshomal;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Created by Mahmood on 10/2/2014.
 */
public class UdpPacket {
    private int port;
    private InetAddress address;
    private int callerSequence;
    private int fragmentNumber;
    private int lastFragmentNumber;
    private byte[] data;

    public int getPort() {
        return port;
    }


    public InetAddress getAddress() {
        return address;
    }

    public int getCallerSequence() {
        return callerSequence;
    }

    public int getFragmentNumber() {
        return fragmentNumber;
    }

    public int getLastFragmentNumber() {
        return lastFragmentNumber;
    }

    public byte[] getData() {
        return data;
    }

    public UdpPacket(int port,InetAddress address,int callerSequence,int fragmentNumber,int lastFragmentNumber,byte[] data)
    {
        this.port=port;
        this.address=address;
        this.callerSequence=callerSequence;
        this.fragmentNumber=fragmentNumber;
        this.lastFragmentNumber=lastFragmentNumber;
        this.data=data;
    }
}
