package com.nasimeshomal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private String routerIPAddress;
    private String password;
    private int portNumber;

    public Config() throws IOException {
        String workingDir = System.getProperty("user.dir");
        String pathToConfigFile=String.format("%s\\%s",workingDir,"Config.txt");

        Properties properties=new Properties();
        InputStream inputStreamForProperties=new FileInputStream(pathToConfigFile);
        properties.load(inputStreamForProperties);

        this.routerIPAddress= properties.getProperty("RouterIPAddress");
        this.password=properties.getProperty("Password");
        this.portNumber=Integer.parseInt(properties.getProperty("PortNumber"));

        inputStreamForProperties.close();
    }

    public String getRouterIPAddress()
    {
        return routerIPAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public int getPortNumber()
    {
        return portNumber;
    }

}
