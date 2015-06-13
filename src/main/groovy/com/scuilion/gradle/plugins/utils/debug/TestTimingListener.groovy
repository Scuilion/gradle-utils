package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult

class TestTimingListener implements TestListener {

    @Override
    void beforeSuite(TestDescriptor suite) {
    }

    @Override
    void afterSuite(TestDescriptor suite, TestResult result) {
    }

    @Override
    void beforeTest(TestDescriptor test) {
        startTime = clock.timeInMs
    }

    @Override
    void afterTest(TestDescriptor test, TestResult result) {
        def paddedTime = (result.endTime - result.startTime).toString().padLeft(7)
        println paddedTime + ' ' + test.toString()
    }
}
