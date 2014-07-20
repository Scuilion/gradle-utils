package com.scuilion.gradle.plugins.utils.init

import org.junit.Test

import static org.junit.Assert.*
import static org.hamcrest.Matchers.containsString

import org.gradle.api.Project
import org.gradle.tooling.GradleConnectionException
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.tooling.GradleConnector

class SyntasticTest{
    
    @Test
    public void checkGeneate(){
        def integrationBuildLocation = new File(System.getProperty('integrationTest.location'), 'syntasticTest')
        def connection = GradleConnector.newConnector().forProjectDirectory(integrationBuildLocation).connect();
        def output = new ByteArrayOutputStream()
        try {
            def build = connection.newBuild()
            build.forTasks('createSyntastic')
            //build.forTasks('tasks')
            //build.setStandardOutput(output)
            build.run()
        } catch (Exception e) {
            throw new GradleConnectionException("Build execution failed.", e);
        } finally {
            connection.close();
        }

        //assertThat(output.toString(), containsString('running alternative class'))
    }

}
