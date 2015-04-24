package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec

class Pather {

    Project project 

    static void addTask(Project project){
        //project.extensions.create("pather", PatherExtension)

        project.task('printClasspath') {
            doLast(){
                project.sourceSets.main.compileClasspath.each{
                    println it
                }
            }
         }
//         project.task('runSimple', type: JavaExec ) {
//             project.afterEvaluate{
//                 main = project.runSimple.mainClass
//                 classpath = project.sourceSets.main.runtimeClasspath
//                 args = project.runSimple.args
//             }
//         }
    }
}
