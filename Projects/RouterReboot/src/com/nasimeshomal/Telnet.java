package com.nasimeshomal;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.OutputStream;

public class Telnet {
    private TelnetClient telnetClient=new TelnetClient();
    private OutputStream outputStream=null;

    public Telnet(String ipAddress,int port) throws IOException {
        telnetClient.connect(ipAddress,port);
        outputStream=telnetClient.getOutputStream();
    }

    public void WriteCommand(String command) throws IOException {
        byte[] commandBytes=command.getBytes();

        outputStream.write(commandBytes);
        outputStream.flush();
    }

    public void DisconnectTelnet() throws IOException {
        outputStream.close();
        telnetClient.disconnect();
    }
}
