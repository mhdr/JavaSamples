package com.nasimeshomal;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

// kernel32.dll uses the __stdcall calling convention (check the function
// declaration for "WINAPI" or "PASCAL"), so extend StdCallLibrary
// Most C libraries will just extend com.sun.jna.Library,
public interface Kernel32 extends StdCallLibrary {

    Kernel32 INSTANCE = (Kernel32)
            Native.loadLibrary("kernel32", Kernel32.class);
    // Optional: wraps every call to the native library in a
    // synchronized block, limiting native calls to one at a time
    Kernel32 SYNC_INSTANCE = (Kernel32)
            Native.synchronizedLibrary(INSTANCE);

    void GetSystemTime(SYSTEMTIME result);
}
