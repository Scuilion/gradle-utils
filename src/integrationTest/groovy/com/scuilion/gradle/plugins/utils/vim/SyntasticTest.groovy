package com.scuilion.gradle.plugins.utils.vim

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
            def listOfFiles = getListOfFiles(syntasticConfig.text)
            println listOfFiles
            assert new File(listOfFiles[0]).exists()
            assert !syntasticConfig.text.contains('/')
            assert syntasticConfig.text.contains(';')
        } else {
            assert !syntasticConfig.text.contains("\\")
            assert !syntasticConfig.text.contains(';')
        }
    }

    def getListOfFiles(def syntaticConfigFile){
        def matching =  syntaticConfigFile =~ /"([^"]*)"/
        def files = matching[0][1].tokenize(";")
        return files
    }
}
