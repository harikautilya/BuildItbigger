package com.udacity.gradle.builditbigger;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskInteractor() {
            @Override
            public void result(String name) {
                assertEquals(name, "Why so serious");
            }
        }).execute();

    }
}
