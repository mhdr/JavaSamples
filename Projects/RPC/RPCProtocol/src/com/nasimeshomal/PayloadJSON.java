package com.nasimeshomal;

import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by Mahmood on 9/26/2014.
 */
public class PayloadJSON extends Payload{
    public PayloadJSON(String methodName, Object parameter) {
        super(methodName, parameter);
    }

    public PayloadJSON(Object returnValue) {
        super(returnValue);
    }

    private String toJSON()
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("methodName",this.getMethodName());
        jsonObject.put("parameter",this.getParameter());
        jsonObject.put("returnValue",this.getReturnValue());
        return jsonObject.toString();
    }

    @Override
    public byte[] Serialize() throws IOException {
        String jsonData=this.toJSON();
        return jsonData.getBytes("UTF-8");
    }
}
