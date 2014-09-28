package com.nasimeshomal;

import org.apache.commons.lang3.StringUtils;

import java.net.DatagramPacket;

/**
 * Created by Mahmood on 9/28/2014.
 */
public class UdpPayloadBinary extends PayloadBinary {

    private DatagramPacket datagramPacket;

    public UdpPayloadBinary(String methodName, Object parameter) {
        super(methodName, parameter);
    }

    public UdpPayloadBinary(Object returnValue) {
        super(returnValue);
    }

    public static UdpPayloadBinary Create(PayloadBinary payloadBinary)
    {
        UdpPayloadBinary payload=null;

        if (StringUtils.isEmpty(payloadBinary.getMethodName()))
        {
            payload=new UdpPayloadBinary(payloadBinary.getReturnValue());
        }
        else
        {
            payload=new UdpPayloadBinary(payloadBinary.getMethodName(),payloadBinary.getParameter());
        }

        return payload;
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }
}
