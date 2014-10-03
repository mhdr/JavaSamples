package com.nasimeshomal;

/**
 * Created by Mahmood on 10/3/2014.
 */
public class UdpSendPacketTable extends UdpPacketTable{
    private static UdpSendPacketTable instance=null;

    private UdpSendPacketTable() {
        super();
    }

    public static UdpSendPacketTable getInstance()
    {
        if (instance==null)
        {
            instance=new UdpSendPacketTable();
        }

        return instance;
    }
}
