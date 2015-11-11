# gradle-utils [![Build Status](https://travis-ci.org/Scuilion/gradle-utils.svg?branch=master)](https://travis-ci.org/Scuilion/gradle-utils)

A gradle plugin to add common task to everyday projects


##### Using the Plugin
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath group: 'com.scuilion.gradle', name: 'utils', version: '0.+'
    }
}

apply plugin: 'utils'
```

Run 'gradle tasks' to see list of added tasks under the 'Uitl tasks' group.

##### Active

* runSimple - simple wrapper around the ExecTask.

Reference a main class in the build file
```groovy
runSimple.mainClass = 'com.RunMain'
```

* printSourceSet - defaults to main. Add property 'sourceSetName' to cmd line to specify a sourceSet.
```bash
gradle printSourceSet -PsourceSetName=test
```

* testTiming - adds very simplified timing to individual test.
```bash
gradle test -PtimeTests
```
___
This is a post processing plugin and must be applied towards the end of your build file.

