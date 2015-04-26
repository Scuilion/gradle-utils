package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.gradle.util.Clock

public class TestTimingListener implements TestListener {

    private Clock clock = new Clock()

    public void beforeSuite(TestDescriptor suite) {
    }

    public void afterSuite(TestDescriptor suite, TestResult result) {
//         String className = suite.getClassName();
//         System.out.printf("Test=%s/%s, tests passed=%d, tests failed=%d, test skipped=%d\n",
//                 suiteName, className, numberOfTestsPassed, numberOfTestsFailed, numberOfTestsSkipped)
    }

    def startTime;

    public void beforeTest(TestDescriptor test) {
        startTime = clock.timeInMs
    }

    public void afterTest(TestDescriptor test, TestResult result) {
        print test.toString() + ' ' 
        println clock.timeInMs - startTime
    }
}
