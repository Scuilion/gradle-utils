package com.scuilion.gradle.plugins.utils.init

import org.junit.Test

import org.gradle.tooling.GradleConnectionException
import org.gradle.tooling.GradleConnector
import org.apache.tools.ant.taskdefs.condition.Os

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
        if (Os.isFamily(Os.FAMILY_WINDOWS)) {
            assert syntasticConfig.text.count(':') <= 1
            assert !syntasticConfig.text.contains('/')
            assert !syntasticConfig.text.contains(';')
        } else {
            assert !syntasticConfig.text.contains("\\")
            assert !syntasticConfig.text.contains(';')
        }
    }
}
