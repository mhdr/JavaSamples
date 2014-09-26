package com.nasimeshomal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws ParseException {
	    String jsonString="{\"FirstName\":\"Mahmood\",\"LastName\":\"Ramzani\",\"Age\":29,\"Years\":[20,30,40,50]}";

        JSONParser parser=new JSONParser();

        Object parsedObj=parser.parse(jsonString);
        JSONObject jsonObject= (JSONObject) parsedObj;

        System.out.print("Object : ");
        System.out.println(jsonObject);

        System.out.print("FirstName : ");
        System.out.println(jsonObject.get("FirstName"));

        System.out.print("LastName : ");
        System.out.println(jsonObject.get("LastName"));

        System.out.print("Age : ");
        System.out.println(jsonObject.get("Age"));


        JSONArray yearsJsonArray= (JSONArray) jsonObject.get("Years");
        System.out.print("Years : ");
        System.out.println(yearsJsonArray);

        Iterator iterator=yearsJsonArray.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
