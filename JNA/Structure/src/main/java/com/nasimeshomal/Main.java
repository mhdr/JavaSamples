package com.nasimeshomal;

public class Main {

    public static void main(String[] args) {
        Kernel32 lib = Kernel32.INSTANCE;
        SYSTEMTIME time = new SYSTEMTIME();
        lib.GetSystemTime(time);

        System.out.println("Today's integer value is " + time.wDay);
    }
}
