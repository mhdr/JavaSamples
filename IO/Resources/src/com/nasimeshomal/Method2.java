package com.nasimeshomal;

import java.io.InputStream;

/**
 * Created by Mahmood on 10/5/2014.
 */
public class Method2 {
    public Method2()
    {
        String resource="/r.txt";
        InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(resource);
        String file=this.getClass().getClassLoader().getResource(resource).getFile();

        System.out.println(inputStream);
        System.out.println(file);
        System.out.println();
    }
}
