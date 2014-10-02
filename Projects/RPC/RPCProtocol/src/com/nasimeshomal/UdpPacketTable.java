package com.nasimeshomal;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.util.ArrayList;

/**
 * Created by Mahmood on 10/3/2014.
 */
public class UdpPacketTable {
    private static UdpPacketTable instance=null;

    private ArrayList<UdpPacket> packets;

    private UdpPacketTable()
    {
        packets=new ArrayList<UdpPacket>();
    }

    public static UdpPacketTable getInstance()
    {
        if (instance==null)
        {
            instance=new UdpPacketTable();
        }

        return instance;
    }

    /**
     * add packet to list
     * @param udpPacket packet to add
     * @return number of remaining packets regardless of missing.
     */
    public int addPacket(UdpPacket udpPacket)
    {
        this.getPackets().add(udpPacket);

        int remain=udpPacket.getLastFragmentNumber()-udpPacket.getFragmentNumber();

        return remain;
    }

    public ArrayList<UdpPacket> getPackets() {
        return packets;
    }
}
