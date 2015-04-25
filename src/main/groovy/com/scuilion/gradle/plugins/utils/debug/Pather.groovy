package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec
import org.gradle.api.UnknownDomainObjectException

class Pather {

    Project project 

    static void addTask(Project project){
        project.extensions.create("pather", PatherExtensions)

        project.task('printSourceSet') {
            project.afterEvaluate{
                def sourceSetName = project.pather.sourceSetName?:'main'
                if(project.sourceSets.findByName(sourceSetName)){
                    project.sourceSets.getByName(sourceSetName).compileClasspath.each{
                        println it
                    }
                }else{
                    throw new UnknownDomainObjectException("'" + sourceSetName +"' is not found. Available Sourcesets: " + project.sourceSets) 
                }
            }
         }
    }
}
