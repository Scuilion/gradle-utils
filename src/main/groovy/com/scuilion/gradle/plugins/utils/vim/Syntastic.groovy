package com.scuilion.gradle.plugins.utils.vim

import org.gradle.api.Project

class Syntastic {

    static void addTask(Project project) {
        project.task('createSyntastic') {
            def classpathFiles = new HashSet<String>()
            def addJars = { proj ->
                addJarDeps(proj, classpathFiles)
                addSrcDirs(proj, classpathFiles)
            }
            project.getChildProjects().each { proj ->
                addJars(proj.value)
            }
            addJars(project.rootProject)

            def configFile = new File(project.rootProject.projectDir.absolutePath + "/.syntastic_javac_config")

            def classpathListed = classpathFiles.collect().join(File.pathSeparator)
            configFile.text = 'let g:syntastic_java_javac_classpath = "' + classpathListed + '"'
        }
    }
    static private void addSrcDirs(project, classpathFiles){
        if(project.hasProperty('sourceSets')){
            project.sourceSets.each { srcSet ->
                srcSet.java.srcDirs.each { dir ->
                    classpathFiles.add(dir.absolutePath)
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
