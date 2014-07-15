package com.scuilion.gradle.plugins.init

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec

class RunSimplePlugin implements Plugin<Project> {

   Project project 

   void apply(Project project) {
      project.extensions.create("runSimple", RunSimplePluginExtension)

      project.task('runSimple', type: JavaExec ) {
         project.afterEvaluate{
             println 'main class is: ' + project.runSimple.mainClass

             main = project.runSimple.mainClass
             classpath = project.sourceSets.main.runtimeClasspath
         }
      }
   }
}
