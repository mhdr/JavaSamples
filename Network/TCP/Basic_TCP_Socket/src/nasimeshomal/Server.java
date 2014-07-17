package nasimeshomal;

import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mahmood on 7/12/2014.
 */
public class Server implements Runnable{

    private ServerSocket serverSocket;
    private ServerEvent serverEvent;
    private Thread thread;
    private boolean stopThread=false;

    public Server(ServerSocket socket)
    {
        this.serverSocket=socket;
    }

    public Server(ServerSocket socket,ServerEvent event)
    {
        this.serverSocket=socket;
        this.serverEvent=event;
    }

    @Override
    public void run() {
        while (!stopThread)
        {

            try {

                sendNewServerMsg(String.format("Listening on port %s ...",serverSocket.getLocalPort()));
                Socket newSocket= serverSocket.accept();

                sendNewServerMsg(String.format("New Client : %s",newSocket.getRemoteSocketAddress()));

                InputStream inputStream=newSocket.getInputStream();
                OutputStream outputStream=newSocket.getOutputStream();

                DataInputStream dataInputStream=new DataInputStream(inputStream);
                DataOutputStream dataOutputStream=new DataOutputStream(outputStream);

                String name= dataInputStream.readUTF();

                sendNewServerMsg(String.format("Server received : %s",name));

                String output=String.format("Hello %s",name);

                dataOutputStream.writeUTF(output);

                dataOutputStream.flush();
                dataInputStream.close();
                dataOutputStream.close();

                newSocket.close();

                sendNewServerMsg(String.format("Server sent : %s",output));

            } catch (IOException e) {
                //e.printStackTrace();
            }


        }
    }

    private void sendNewServerMsg(final String msg)
    {
        if (serverEvent!=null)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverEvent.NewServerMsg(msg);
                }
            });

        }
    }

    public void start()
    {
        if (thread==null)
        {
            stopThread=false;

            thread=new Thread(this);
            thread.start();
        }
    }

    public void stop() throws IOException, InterruptedException {
        if (thread!=null)
        {
            stopThread=true;
            serverSocket.close();
        }
    }
}
