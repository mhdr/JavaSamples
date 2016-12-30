package com.nasimeshomal;

import com.sun.jna.platform.win32.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Kernel32Util.getTempPath());
    }
}
