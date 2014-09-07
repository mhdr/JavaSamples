package com.nasimeshomal;

/**
 * Created by Mahmood on 9/7/2014.
 */
public class Greeting {

    public void sayHello(String name)
    {
        System.out.println(this.getGreeting(name));
    }

    public String getGreeting(String name)
    {
        return String.format("Hello %s",name);
    }
}
