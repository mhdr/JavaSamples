package com.nasimeshomal;

import org.apache.commons.lang3.StringUtils;

import java.net.DatagramPacket;

/**
 * Created by Mahmood on 9/28/2014.
 */
public class UdpPayloadJSON extends PayloadJSON {

    private DatagramPacket datagramPacket;

    public UdpPayloadJSON(String methodName, Object parameter) {
        super(methodName, parameter);
    }

    public UdpPayloadJSON(Object returnValue) {
        super(returnValue);
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }

    public static UdpPayloadJSON Create(PayloadJSON payloadJSON)
    {
        UdpPayloadJSON payload=null;

        if (StringUtils.isEmpty(payloadJSON.getMethodName()))
        {
            payload=new UdpPayloadJSON(payloadJSON.getReturnValue());
        }
        else
        {
            payload=new UdpPayloadJSON(payloadJSON.getMethodName(),payloadJSON.getParameter());
        }

        return payload;
    }
}
