package nasimeshomal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;

/**
 * Created by Mahmood on 10/5/2014.
 */
public class Config {
    private String path;

    public Config() throws IOException {
        String workingDir=System.getProperty("user.dir");
        path=String.format("%s\\%s",workingDir,"config.json");

        File file=new File(path);

        if (!file.exists())
        {
            this.CreateConfigFile();
            this.InitializeConfigFile();
        }
    }

    private boolean CreateConfigFile() throws IOException {
        File file=new File(path);
        return file.createNewFile();
    }

    private void InitializeConfigFile() throws IOException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("LastFolder","");

        String jsonOutput=jsonObject.toJSONString();
        byte[] jsonOutputByte=jsonOutput.getBytes("UTF-8");

        FileOutputStream fileOutputStream=new FileOutputStream(path);
        fileOutputStream.write(jsonOutputByte);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private JSONObject ReadJson() throws IOException, ParseException {
        FileInputStream fileInputStream=new FileInputStream(path);
        int size=fileInputStream.available();
        byte[] jsonBytes=new byte[size];

        fileInputStream.read(jsonBytes);
        String jsonStr=new String(jsonBytes,"UTF-8");

        JSONParser parser=new JSONParser();
        Object parsedObject=parser.parse(jsonStr);
        JSONObject jsonObject= (JSONObject) parsedObject;

        fileInputStream.close();

        return jsonObject;
    }

    public String getLastFolder() throws IOException, ParseException {
        JSONObject jsonObject=this.ReadJson();
        return (String) jsonObject.get("LastFolder");
    }

    public void setLastFolder(String lastFile) throws IOException, ParseException {
        JSONObject jsonObject=this.ReadJson();
        jsonObject.put("LastFolder",lastFile);
        String jsonOutput=jsonObject.toJSONString();
        byte[] jsonBytes=jsonOutput.getBytes("UTF-8");

        FileOutputStream fileOutputStream=new FileOutputStream(path);
        fileOutputStream.write(jsonBytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
