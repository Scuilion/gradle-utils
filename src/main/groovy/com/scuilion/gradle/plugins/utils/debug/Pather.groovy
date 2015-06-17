package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException
import com.scuilion.gradle.plugins.utils.UtilsProperties

class Pather {

    static void addTask(Project project) {

        project.extensions.create("pather", PatherExtensions)

        project.task('printSourceSet') {
            def sourceSetName
            project.afterEvaluate {
                sourceSetName = getSourceSetName(project)
                checkSourceSetName(project, sourceSetName)
            }
            doLast {
                project.sourceSets.getByName(sourceSetName).compileClasspath.each {
                    println it
                }
            }
        }
        project.tasks.printSourceSet.group = UtilsProperties.GROUP
        project.tasks.printSourceSet.description =
            "Print source set. Defaults to 'main'. Set 'sourceSetName' property from cmd-line"
    }

    static String getSourceSetName(Project project) {
        def sourceSetName = project.pather.sourceSetName ?: 'main'
        if (project.hasProperty('sourceSetName')) {
            //set from command line
            sourceSetName = project.ext.sourceSetName
        }
        return sourceSetName
    }

    static void checkSourceSetName(Project project, String sourceSetName) {
        if (project.sourceSets.findByName(sourceSetName) == null) {
            def exceptionMessage = "'${sourceSetName}' is not found. Available Sourcesets: ${project.sourceSets}"
            throw new UnknownDomainObjectException(exceptionMessage)
        }
    }
}
