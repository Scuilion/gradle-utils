package com.scuilion.gradle.plugins.init

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec
import org.gradle.testfixtures.ProjectBuilder

import org.junit.Test

class RunSimpleTest {

    @Test
    public void createBasicPlugin(){
        Project project = ProjectBuilder.builder().withName('RunSimple').build()
        project.apply plugin: 'run-simple'
        def runSimpleTask = project.tasks.runSimple
        assert runSimpleTask instanceof JavaExec
    }
}
