package com.nasimeshomal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class Log {

    private FileOutputStream log=null;

    public Log() throws IOException {

        String workingDir = System.getProperty("user.dir");
        String pathToLogFile=String.format("%s\\%s",workingDir,"log.txt");

        File logFile=new File(pathToLogFile);
        boolean fileCreationResult=false;

        if (!logFile.exists())
        {
            fileCreationResult=logFile.createNewFile();
        }

        if (fileCreationResult)
        {
            log=new FileOutputStream(logFile,true);
        }
    }

    public void WriteLog(String msg) throws IOException {
        if (log!=null)
        {
            log.write(msg.getBytes());
            log.flush();
        }
    }

    private void WriteIPsToLog() throws IOException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dateobj = new Date();
        String currentDate= df.format(dateobj);

        this.WriteLog(String.format("Date : %s\r\n",currentDate));

        Enumeration<NetworkInterface> interfaces= NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements())
        {
            NetworkInterface networkInterface= interfaces.nextElement();
            Enumeration<InetAddress> addresses= networkInterface.getInetAddresses();

            while (addresses.hasMoreElements())
            {
                InetAddress address= addresses.nextElement();
                String output=String.format("           %s : %s\r\n",networkInterface.getDisplayName() ,address.getHostAddress());
                this.WriteLog(output);
            }
        }
    }

    public void WriteLogBeforeReboot() throws IOException {
        this.WriteLog("*Before Reboot*\r\n");
        this.WriteIPsToLog();
        this.WriteLog("\r\n");
    }

    public void WriteLogAfterReboot() throws IOException {
        this.WriteLog("*After Reboot*\r\n");
        this.WriteIPsToLog();
        this.WriteLog("\r\n");
        this.WriteLog("==========================================================\r\n");
        this.WriteLog("\r\n");
    }

    public void CloseLog() throws IOException {
        if (log!=null)
        {
            log.close();
        }
    }
}
