package com.scuilion.gradle.plugins.utils.vim

import org.gradle.api.Project
import com.scuilion.gradle.plugins.utils.UtilsProperties
import org.apache.commons.lang3.SystemUtils

class Syntastic {

    private static final String SYNTASTIC_TASK_NAME = 'createSyntastic'

    static void addTask(Project project) {
        project.task(SYNTASTIC_TASK_NAME) {
            def classpathFiles = [] as Set
            def addJars = { proj ->
                addJarDeps(proj, classpathFiles)
                addSrcDirs(proj, classpathFiles)
            }

            project.childProjects.each { proj ->
                addJars(proj.value)
            }
            addJars(project.rootProject)
            def outputFile = project.file(project.rootProject.projectDir.absolutePath + "/.syntastic_javac_config")
            inputs.property "classpath", getClassPathListed(classpathFiles)
            outputs.file outputFile
            doLast {
                outputFile.write ''
                outputFile.write inputs.properties.classpath
            }
        }

        attachTo(project, 'compileJava')
        attachTo(project, 'compileGroovy')
        project.tasks.createSyntastic.group = UtilsProperties.GROUP
        project.tasks.createSyntastic.description =
            """Attaches to the 'compileJava' and 'compileGroovy' tasks.
            Generates classpath and saves it as .syntastic_javac_config"""
    }

    static private void attachTo(Project project, String lang) {
        if (project.tasks.findByName(lang)) {
            project.tasks.getByName(lang).dependsOn(SYNTASTIC_TASK_NAME)
        }
    }

    static private String getClassPathListed(def classpathFiles) {
        //def classpathList = getUnifiedPath(classpathFiles)  assume the path separator is correct.
        return 'let g:syntastic_java_javac_classpath = "' + classpathFiles.join(joinCharacter) + '"'
    }

//     static private String getUnifiedPath(def classpathFiles) {
//         classpathFiles.collect {
//             println it.canonicalPath
//             it.canonicalPath.replaceAll('\\\\', pathSeperator)
//         }
//     }
//
//     static private String getPathSeperator() {
//         //return File.separator
//         def seperator = '/'
//         if (SystemUtils.IS_OS_WINDOWS) {
//             seperator = "\\\\"
//         }
//         return seperator
//     }

    static private String getJoinCharacter() {
        return File.pathSeparator
//          def joinCharacter = ':'
//          if (SystemUtils.IS_OS_WINDOWS) {
//              joinCharacter = ';'
//          }
//          return joinCharacter
    }

    static private void addSrcDirs(project, classpathFiles) {
        if (project.hasProperty('sourceSets')) {
            project.sourceSets.each { srcSet ->
                srcSet.java.srcDirs.each { dir ->
                    classpathFiles.add(new File(dir.absolutePath))
                }
            }
        }
    }

    static private void addJarDeps(Project project, Set<String> classpathFiles) {
        project.configurations.each { conf ->
            conf.each { jar ->
                classpathFiles.add(jar)
            }
        }
    }
}
