package nasimeshomal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Mahmood on 7/18/2014.
 */
public class Config {
    private String serverIPAddress;
    private int portNumber;

    public Config() throws IOException {
        String workingDir = System.getProperty("user.dir");

        String osType=System.getProperty("os.name").toLowerCase();

        String pathToConfigFile="";


        if (osType.equals("linux"))
        {
             pathToConfigFile=String.format("%s/%s",workingDir,"Config.txt");
        }
        else
        {
             pathToConfigFile=String.format("%s\\%s",workingDir,"Config.txt");
        }


        Properties properties=new Properties();
        InputStream inputStreamForProperties=new FileInputStream(pathToConfigFile);
        properties.load(inputStreamForProperties);

        this.serverIPAddress = properties.getProperty("ServerIPAddress");
        this.portNumber=Integer.parseInt(properties.getProperty("PortNumber"));

        inputStreamForProperties.close();
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public int getPortNumber()
    {
        return portNumber;
    }

}
