package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by Mahmood on 9/9/2014.
 */
public class TcpRPC {
    private Socket socket;

    public TcpRPC(Socket socket)
    {
        this.setSocket(socket);
    }

    public byte[] Serialize(Payload payload) throws IOException {
        byte[] msgByte=payload.Serialize();
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byteArrayOutputStream.write(sizeOfMsgByte);
        byteArrayOutputStream.write(msgByte);
        byte[] dataToSend=byteArrayOutputStream.toByteArray();

        return dataToSend;
    }

    public void Send(Payload payload) throws IOException {
        byte[] dataToSend= this.Serialize(payload);
        OutputStream outputStream=this.socket.getOutputStream();
        outputStream.write(dataToSend);
        outputStream.flush();
    }

    public PayloadBinary ReceiveBinary() throws IOException, ClassNotFoundException {
        InputStream inputStream=this.socket.getInputStream();

        byte[] sizeofMsgByte=new byte[4];
        inputStream.read(sizeofMsgByte);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
        byte[] data=new byte[sizeOfMsg];
        inputStream.read(data);

        PayloadBinary payload= Payload.DeserializeFromBinary(data);

        return payload;
    }

    public PayloadJSON ReceiveJSON() throws IOException, ClassNotFoundException, ParseException {
        InputStream inputStream=this.socket.getInputStream();

        byte[] sizeofMsgByte=new byte[4];
        inputStream.read(sizeofMsgByte);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
        byte[] data=new byte[sizeOfMsg];
        inputStream.read(data);

        PayloadJSON payload= Payload.DeserializeFromJSON(data);

        return payload;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void CloseSocket() throws IOException {
        this.socket.close();
    }
}
