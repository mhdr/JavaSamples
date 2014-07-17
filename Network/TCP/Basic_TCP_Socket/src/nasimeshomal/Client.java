package nasimeshomal;

import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Mahmood on 7/12/2014.
 */
public class Client {

    private int port=9001;
    private String host="127.0.0.1";
    private ClientEvent clientEvent;
    private Thread thread;
    private boolean stopThread=false;

    public Client()
    {

    }

    public Client(String host,int port)
    {
        this.host=host;
        this.port=port;
    }

    public Client(String host,int port,ClientEvent event)
    {
        this.host=host;
        this.port=port;
        this.clientEvent=event;
    }

    public void SendMsg(String msg) throws IOException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket=new Socket(host,port);

                    InputStream inputStream=socket.getInputStream();
                    OutputStream outputStream=socket.getOutputStream();

                    DataInputStream dataInputStream=new DataInputStream(inputStream);
                    DataOutputStream dataOutputStream=new DataOutputStream(outputStream);

                    dataOutputStream.writeUTF(msg);

                    outputStream.flush();

                    sendNewClientMsg(String.format("Client Sent : %s",msg));

                    String newMsg= dataInputStream.readUTF();
                    sendNewClientMsg(String.format("Client Received : %s",newMsg));

                    dataInputStream.close();
                    outputStream.close();

                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    private void sendNewClientMsg(final String msg)
    {
        if (this.clientEvent !=null)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    clientEvent.NewClientMsg(msg);
                }
            });
        }
    }
}
