package com.nasimeshomal;

/**
 * Created by Mahmood on 10/2/2014.
 */
public class CallerSequence {

    private static int id;

    public static int getNextId()
    {
        id +=1;
        return id;
    }

    public static int getLastId() {
        return id;
    }
}
