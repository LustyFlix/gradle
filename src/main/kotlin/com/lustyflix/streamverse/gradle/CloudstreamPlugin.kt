package com.lustyflix.streamverse.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.lustyflix.streamverse.gradle.tasks.registerTasks
import com.lustyflix.streamverse.gradle.configuration.registerConfigurations

abstract class StreamversePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("streamverse", StreamverseExtension::class.java, project)

        registerTasks(project)
        registerConfigurations(project)
    }
}