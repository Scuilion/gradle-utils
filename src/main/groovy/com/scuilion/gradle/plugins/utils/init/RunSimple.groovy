package com.scuilion.gradle.plugins.utils.init

import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec
import com.scuilion.gradle.plugins.utils.UtilsProperties

class RunSimple {

    private static final String RUN_SIMPLE = 'runSimple'

    static void addTask(Project project) {
        project.extensions.create(RUN_SIMPLE, RunSimpleExtensions)

        project.task(RUN_SIMPLE, type: JavaExec ) {
            project.afterEvaluate {
                main = project.runSimple.mainClass
                classpath = project.sourceSets.main.runtimeClasspath
                args = project.runSimple.args
            }
        }
        project.tasks.runSimple.group = UtilsProperties.GROUP
        project.tasks.runSimple.description =
            "JavaExec wrapper. Set property 'runSimple.mainClass' and 'runSimple.args'"
    }
}
