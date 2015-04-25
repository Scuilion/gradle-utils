package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec
import org.gradle.api.UnknownDomainObjectException

class Pather {

    static void addTask(Project project){

        project.extensions.create("pather", PatherExtensions)

        project.task('printSourceSet') {
            project.afterEvaluate{
                def sourceSetName = getSourceSetName(project)
                checkSourceSetName(project, sourceSetName)
                project.sourceSets.getByName(sourceSetName).compileClasspath.each{
                    println it
                }
            }
         }
    }

    static String getSourceSetName(Project project){
        def sourceSetName = project.pather.sourceSetName?:'main'
        if(project.hasProperty('sourceSetName')){
            //set from command line
            sourceSetName = project.ext.sourceSetName
        }
        return sourceSetName
    }

    static void checkSourceSetName(Project project, String sourceSetName){
        if(project.sourceSets.findByName(sourceSetName) == null){
            throw new UnknownDomainObjectException("'" + sourceSetName +"' is not found. Available Sourcesets: " + project.sourceSets) 
        }
    }
}
