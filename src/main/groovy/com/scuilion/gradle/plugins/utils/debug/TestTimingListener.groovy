package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.gradle.util.Clock

public class TestTimingListener implements TestListener {

    private Clock clock = new Clock()
    def startTime;

    public void beforeSuite(TestDescriptor suite) {
    }

    public void afterSuite(TestDescriptor suite, TestResult result) {
    }

    public void beforeTest(TestDescriptor test) {
        startTime = clock.timeInMs
    }

    public void afterTest(TestDescriptor test, TestResult result) {
        def paddedTime = (clock.timeInMs - startTime).toString().padLeft(7) 
        println paddedTime + ' ' + test.toString()
    }
}
