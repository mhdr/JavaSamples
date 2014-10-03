package com.nasimeshomal;

/**
 * Created by Mahmood on 10/3/2014.
 */
public class UdpReceivePacketTable extends UdpPacketTable{

    private static UdpReceivePacketTable instance=null;

    private UdpReceivePacketTable() {
        super();
    }

    public static UdpReceivePacketTable getInstance()
    {
        if (instance==null)
        {
            instance=new UdpReceivePacketTable();
        }

        return instance;
    }
}
