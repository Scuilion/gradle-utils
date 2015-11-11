package com.scuilion.gradle.plugins.utils

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.scuilion.gradle.plugins.utils.init.RunSimple
import com.scuilion.gradle.plugins.utils.debug.Pather
import com.scuilion.gradle.plugins.utils.debug.TestTiming

class UtilsPlugin implements Plugin<Project> {

    void apply(Project project) {
        RunSimple.addTask(project)
        Pather.addTask(project)
        TestTiming.addTask(project)
    }
}
