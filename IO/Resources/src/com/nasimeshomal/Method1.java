package com.nasimeshomal;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Mahmood on 10/5/2014.
 */
public class Method1 {
    public Method1()
    {
        String resource="resources/r.txt";
        InputStream inputStream=this.getClass().getResourceAsStream(resource);
        String file=this.getClass().getResource(resource).getFile();

        System.out.println(inputStream);
        System.out.println(file);
        System.out.println();
    }
}
