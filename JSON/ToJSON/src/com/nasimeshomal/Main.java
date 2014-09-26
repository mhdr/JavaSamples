package com.nasimeshomal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FirstName","Mahmood");
        jsonObject.put("LastName","Ramzani");
        jsonObject.put("Age",29);

        JSONArray years=new JSONArray();
        years.add(20);
        years.add(30);
        years.add(40);
        years.add(50);
        jsonObject.put("Years",years);

        System.out.print("Object : ");
        System.out.println(jsonObject);

        System.out.print("to String : ");
        System.out.println(jsonObject.toString());

        System.out.print("to JSON String : ");
        System.out.println(jsonObject.toJSONString());
    }
}
