package com.scuilion.gradle.plugins.init

import org.junit.Test

import org.gradle.api.Project
import org.gradle.tooling.GradleConnectionException
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.tooling.GradleConnector

class PluginTest{
    
    @Test
    public void checkRun(){
        def integrationBuildLocation = new File(System.getProperty('integrationTest.location'), 'runSimple')
        def connection = GradleConnector.newConnector().forProjectDirectory(integrationBuildLocation).connect();
        try {
            def build = connection.newBuild()
            build.forTasks('runSimple')
            build.run()
        } catch (Exception e) {
            throw new GradleConnectionException("Build execution failed.", e);
        } finally {
            connection.close();
        }
    }

}
