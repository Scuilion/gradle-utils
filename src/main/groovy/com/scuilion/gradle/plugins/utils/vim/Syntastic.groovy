package com.scuilion.gradle.plugins.utils.vim

import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class Syntastic {

    static void addTask(Project project) {
        project.task('createSyntastic') {
            def classpathFiles = new HashSet<File>()
            def addJars = { proj ->
                addJarDeps(proj, classpathFiles)
                addSrcDirs(proj, classpathFiles)
            }

            project.getChildProjects().each { proj ->
                addJars(proj.value)
            }
            addJars(project.rootProject)
            def outputFile = project.file(project.rootProject.projectDir.absolutePath + "/.syntastic_javac_config")
            inputs.property "classpath", getClassPathListed(classpathFiles) 
            outputs.file outputFile
            doLast{
                outputFile.write ''
                outputFile.write inputs.properties.classpath
            }
        }
        attachTo(project, 'compileJava')
        attachTo(project, 'compileGroovy')
    }

    static private void attachTo(Project project, String lang){
        if(project.tasks.findByName(lang)){
            project.tasks.getByName(lang).dependsOn('createSyntastic')
        }
    }

    static private String getClassPathListed(def classpathFiles){
        return 'let g:syntastic_java_javac_classpath = "' + classpathFiles.collect({it.getCanonicalPath().replaceAll('\\\\', '/')}).join(';') + '"'
    }

    static private void addSrcDirs(project, classpathFiles){
        if(project.hasProperty('sourceSets')){
            project.sourceSets.each { srcSet ->
                srcSet.java.srcDirs.each { dir ->
                    classpathFiles.add(new File(dir.absolutePath))
                }
            }
        }
    }

    static private void addJarDeps(Project project, HashSet<String> classpathFiles){
        project.configurations.each { conf ->
            conf.each { jar ->
                classpathFiles.add(jar)
            }
        }
    }
}
