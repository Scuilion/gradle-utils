package com.scuilion.gradle.plugins.init

import org.junit.Test

import org.gradle.api.Project
import org.gradle.tooling.GradleConnectionException
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.tooling.GradleConnector

class PluginTest{
    
    @Test
    public void checkRun(){
        def connection = GradleConnector.newConnector().forProjectDirectory(new File('buier')).connect();
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
