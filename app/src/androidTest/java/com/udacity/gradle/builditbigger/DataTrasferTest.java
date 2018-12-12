package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class DataTrasferTest extends InstrumentationTestCase {

    ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class, true);

    @Test
    public void testDataRequest() throws Throwable {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        final CountDownLatch signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskInteractor() {
                    @Override
                    public void result(String name) {
                        assertEquals(name, "Why so serious");
                        signal.countDown();
                    }
                }).execute();
            }
        });
        signal.await(30, TimeUnit.SECONDS);
    }
}
