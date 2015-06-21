package com.scuilion.gradle.plugins.utils.debug

import com.scuilion.gradle.plugins.utils.UtilsProperties
import org.gradle.api.Project

class TestTiming {

    static void addTask(Project project) {

        project.task('testTiming') {
        }

        if (project.hasProperty('timeTests')) {
            project.rootProject.gradle.addListener new TestTimingListener()
        }

        project.tasks.testTiming.group = UtilsProperties.GROUP
        project.tasks.testTiming.description = "Add property 'timeTests' to get test runtime."
    }
}
