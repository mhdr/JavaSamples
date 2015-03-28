package com.nasimeshomal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Config config=new Config();
        Log log=new Log();

        String ipAddress= config.getRouterIPAddress();
        String password=config.getPassword();
        int port=config.getPortNumber();

        Telnet telnet=new Telnet(ipAddress,port);

        String command1=String.format("%s\n",password);
        String command2="set reboot\n";

        Thread.sleep(1000);

        log.WriteLogBeforeReboot();

        telnet.WriteCommand(command1);

        Thread.sleep(1000);

        telnet.WriteCommand(command2);

        telnet.DisconnectTelnet();

        Thread.sleep(2*60*1000);

        log.WriteLogAfterReboot();
        log.CloseLog();
    }
}
