package com.nasimeshomal;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class SYSTEMTIME extends Structure {
    public short wYear;
    public short wMonth;
    public short wDayOfWeek;
    public short wDay;
    public short wHour;
    public short wMinute;
    public short wSecond;
    public short wMilliseconds;
    protected List getFieldOrder() {
        return Arrays.asList(new String[] {
                "wYear", "wMonth", "wDayOfWeek", "wDay", "wHour", "wMinute", "wSecond", "wMilliseconds",
        });
    }
}

