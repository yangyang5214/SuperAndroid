package com.example.administrator.superandroid;

import com.example.administrator.superandroid.util.StringUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public  void getRandomString(){
        System.out.println(StringUtil.getRandomString(4));
    }
}