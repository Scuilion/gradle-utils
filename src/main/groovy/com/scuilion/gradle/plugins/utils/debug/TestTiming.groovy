package com.scuilion.gradle.plugins.utils.debug

import com.scuilion.gradle.plugins.utils.UtilsProperties
import org.gradle.api.Project

public class TestTiming {

    static void addTask(Project project){

        project.extensions.create("testTiming", TestTimingExtensions)

        project.task('testTiming') {
        }

        if(project.hasProperty('timeTests')) {
            project.gradle.addListener new TestTiming()
        } 

        project.tasks.testTiming.group = UtilsProperties.GROUP 
        project.tasks.testTiming.description = "Add property 'timeTests' to get test runtime."
    }
}
