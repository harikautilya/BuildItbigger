package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DataTrasferTest {

    @Test
    public void testDataRequest(){
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskInteractor() {
            @Override
            public void result(String name) {
                assertEquals(name, "Why so serious");
            }
        }).execute();
    }
}
