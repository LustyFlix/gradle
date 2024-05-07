package com.lustyflix.streamverse.gradle.tasks

import com.lustyflix.streamverse.gradle.getStreamverse
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.util.function.Function
import java.net.URL
import com.lustyflix.streamverse.gradle.download
import com.lustyflix.streamverse.gradle.createProgressLogger

abstract class GenSourcesTask : DefaultTask() {
    @TaskAction
    fun genSources() {
        val extension = project.extensions.getStreamverse()
        val apkinfo = extension.apkinfo!!

        val sourcesJarFile = apkinfo.cache.resolve("streamverse-sources.jar")

        val url = URL("${apkinfo.urlPrefix}/app-sources.jar")

        url.download(sourcesJarFile, createProgressLogger(project, "Download sources"))
    }
}