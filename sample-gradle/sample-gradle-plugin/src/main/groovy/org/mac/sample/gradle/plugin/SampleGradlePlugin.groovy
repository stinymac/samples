package org.mac.sample.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SampleGradlePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println "Hello ${project.name},I am  sample gradle plugin!"
    }
}