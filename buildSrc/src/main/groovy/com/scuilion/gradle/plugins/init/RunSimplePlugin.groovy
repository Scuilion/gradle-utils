package com.scuilion.gradle.plugins.init

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.GroovyPlugin

import org.apache.commons.lang.SystemUtils

class RunSimplePlugin implements Plugin<Project> {

   Project project 

   void apply(Project project) {
      project.extensions.runSimple = new RunSimplePluginExtension()
      project.plugins.apply(BasePlugin.class)
      project.plugins.apply(GroovyPlugin.class)

      project.task('runSimple', type: JavaExec ) {
         println 'main class is: ' + project.runSimple.mainClass
         main = project.runSimple.mainClass                   
         classpath = project.sourceSets.main.runtimeClasspath
      }

      println SystemUtils.IS_OS_WINDOWS 

   }
}
