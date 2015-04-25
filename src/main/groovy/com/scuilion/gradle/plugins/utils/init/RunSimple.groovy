package com.scuilion.gradle.plugins.utils.init

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec
import com.scuilion.gradle.plugins.utils.UtilsProperties

class RunSimple {

    Project project 

    static void addTask(Project project){
        project.extensions.create("runSimple", RunSimpleExtensions)

        project.task('runSimple', type: JavaExec ) {
            project.afterEvaluate{
                main = project.runSimple.mainClass
                classpath = project.sourceSets.main.runtimeClasspath
                args = project.runSimple.args
            }
        }
        project.tasks.runSimple.group = UtilsProperties.GROUP 
        project.tasks.runSimple.description = "JavaExec wrapper. Set property 'runSimple.mainClass' and 'runSimple.args'"
    }
}
