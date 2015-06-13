package com.scuilion.gradle.plugins.utils.debug

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import org.junit.Test

class PatherTest {

    @Test
    void noSourceSetSpecified() {
        Project project = ProjectBuilder.builder().withName('PatherPluginTest').build()
        project.apply plugin: 'utils'
        Pather pather = new Pather()
        assert 'main' == pather.getSourceSetName(project)
    }

    @Test
    void setItOnAProperty() {
        Project project = ProjectBuilder.builder().withName('PatherPluginTest').build()
        project.apply plugin: 'utils'
        project.ext.sourceSetName = 'special'
        Pather pather = new Pather()
        pather.getSourceSetName(project)
        assert 'special' == pather.getSourceSetName(project)
    }
}
