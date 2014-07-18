package nasimeshomal;

/**
 * Created by Mahmood on 7/18/2014.
 */
public class ServerConfig {
    private static String serverIPAddress;
    private static int portNumber;

    public static String getServerIPAddress() {
        return serverIPAddress;
    }

    public static void setServerIPAddress(String serverIPAddress) {
        ServerConfig.serverIPAddress = serverIPAddress;
    }

    public static int getPortNumber() {
        return portNumber;
    }

    public static void setPortNumber(int portNumber) {
        ServerConfig.portNumber = portNumber;
    }
}
