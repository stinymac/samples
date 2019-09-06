package org.mac.sample.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SampleGradlePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println "===== Hello ${project.name}======="
        project.extensions.create('releaseNote',SampleGradlePluginExtension)
        project.tasks.create('writeReleaseNoteTask', SampleGradleTask)
        println "===== ${project.name} execute plugin success======="
    }
}