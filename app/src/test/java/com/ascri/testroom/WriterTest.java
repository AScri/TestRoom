package com.ascri.testroom;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WriterTest {
    @Rule
    public MockitoRule mockRule = MockitoJUnit.rule();

    @Mock
    Context context;

    @Mock
    FileOutputStream fileOutputStream;

    @Test
    public void testWriter(){
        try {
            when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);
            Util.writeConfiguration(context);
            verify(context, times(1)).openFileOutput(anyString(), anyInt());
            verify(fileOutputStream, atLeast(2)).write(any(byte[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
