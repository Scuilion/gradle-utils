# gradle-utils

A person gradle plugin to work better.

##### Added Task 

* runSimple
Reference a main class in the build file
```groovy
runSimple.mainClass = 'com.RunMain'
```

* createSyntastic
Creates a .syntastic_javac_config file in the root of the project.

This is a post processing plugin and must be applied towards the end of your build file.

