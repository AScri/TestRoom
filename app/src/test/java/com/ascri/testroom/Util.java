package com.ascri.testroom;

import android.content.Context;

import java.io.FileOutputStream;

public class Util {
    public static void writeConfiguration(Context ctx) {
        try (FileOutputStream openFileOutput =
                     ctx.openFileOutput("config.txt", Context.MODE_PRIVATE)) {
            openFileOutput.write("This is a test1.".getBytes());
            openFileOutput.write("This is a test2.".getBytes());
        } catch (Exception e) {
            // not handledавпавп
            //testfffffffffffffffffffff
            //Test branch
            //another Test
            //++ee
            //change user
        }
    }
}
