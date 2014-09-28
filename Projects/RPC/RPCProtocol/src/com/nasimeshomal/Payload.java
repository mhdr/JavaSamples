package com.nasimeshomal;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * Created by Mahmood on 9/12/2014.
 */
public abstract class Payload implements Serializable{
    private String methodName;
    private Object parameter;
    private Object returnValue;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Payload(String methodName, Object parameter)
    {
        this.methodName=methodName;
        this.parameter=parameter;
    }

    public Payload(Object returnValue)
    {
        this.returnValue=returnValue;
    }

    public static PayloadBinary DeserializeFromBinary(byte[] payload) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(payload);
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);

        PayloadBinary payloadBinary= (PayloadBinary) objectInputStream.readObject();

        return payloadBinary;
    }

    public static PayloadJSON DeserializeFromJSON(byte[] payload) throws UnsupportedEncodingException, ParseException {
        String jsonData=new String(payload,"UTF-8");
        JSONParser parser=new JSONParser();
        JSONObject jsonObject= (JSONObject) parser.parse(jsonData);

        String methodName= (String) jsonObject.get("methodName");
        Object parameter=jsonObject.get("parameter");
        Object returnValue=jsonObject.get("returnValue");

        PayloadJSON newPayload=null;

        if (StringUtils.isEmpty(methodName))
        {
            newPayload=new PayloadJSON(returnValue);
        }
        else
        {
            newPayload=new PayloadJSON(methodName,parameter);
        }

        return newPayload;
    }

    public abstract byte[] Serialize() throws IOException;

}
