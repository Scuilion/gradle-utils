package com.scuilion.gradle.plugins.utils.init

import spock.lang.*

import org.junit.rules.TemporaryFolder

import org.junit.Test
import org.junit.ClassRule

import org.gradle.testkit.runner.GradleRunner
import org.gradle.tooling.GradleConnectionException
import org.gradle.tooling.GradleConnector

class RunSimpleTest extends Specification {

    @ClassRule @Shared
    TemporaryFolder testProjectDir = new TemporaryFolder()

    def runSimpleClass
    def buildFile
    def result

    def setup() {

        buildFile = testProjectDir.newFile('build.gradle')
        def srcLocation = testProjectDir.newFolder('src', 'main', 'groovy', 'com')
        srcLocation.mkdirs()
        runSimpleClass = new File(srcLocation, 'RunSimple.groovy')

        def pluginClasspathResource = getClass().classLoader.findResource("plugin-classpath.txt")
        if (pluginClasspathResource == null) {
            throw new IllegalStateException("Did not find plugin classpath resource, run `testClasses` build task.")
        }

        def pluginClasspath = pluginClasspathResource.readLines()
            .collect { it.replace('\\', '\\\\') } // escape backslashes in Windows paths
            .collect { "'$it'" }
            .join(", ")

        buildFile << """
            apply plugin: 'maven'

            buildscript {
                dependencies {
                    classpath files($pluginClasspath)
                }
            }
            apply plugin: 'groovy'
            dependencies {
                compile localGroovy()
            }
            apply plugin: com.scuilion.gradle.plugins.utils.UtilsPlugin
            runSimple.mainClass = 'com.RunSimple'
        """
        runSimpleClass << """
            package com;
            class RunSimple{

            public static void main(String[] args){
                println 'in run simple from integration test'
            }
        }
        """
        result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments('runSimple')
            .build()
    }
    def "main class was run"() {
        expect:
            result.standardOutput.contains('in run simple from integration test')
    }
}
