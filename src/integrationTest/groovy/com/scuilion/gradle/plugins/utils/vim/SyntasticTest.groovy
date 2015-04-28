package com.scuilion.gradle.plugins.utils.init

import org.junit.Test

import org.gradle.tooling.GradleConnectionException
import org.gradle.tooling.GradleConnector

class SyntasticTest {

    @Test
    void checkGeneate() {
        def integrationBuildLocation = new File(System.getProperty('integrationTest.location'), 'syntasticTest')
        def connection = GradleConnector.newConnector().forProjectDirectory(integrationBuildLocation).connect()
        try {
            def build = connection.newBuild()
            build.forTasks('createSyntastic')
            build.run()
        } catch (Exception e) {
            throw new GradleConnectionException("Build execution failed.", e)
        } finally {
            connection.close()
        }
        def syntasticConfig = new File(integrationBuildLocation, ".syntastic_javac_config")
        assert syntasticConfig.exists()
    }

}
