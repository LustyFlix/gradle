package com.lustyflix.streamverse.gradle.configuration

import com.lustyflix.streamverse.gradle.ApkInfo
import com.lustyflix.streamverse.gradle.createProgressLogger
import com.lustyflix.streamverse.gradle.download
import com.lustyflix.streamverse.gradle.getStreamverse
import groovy.json.JsonSlurper
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import java.lang.Integer.parseInt
import java.net.URL
import java.nio.file.Files

class ApkConfigurationProvider : IConfigurationProvider {

    override val name: String
        get() = "apk"

    override fun provide(project: Project, dependency: Dependency) {
        val extension = project.extensions.getStreamverse()
        if (extension.apkinfo == null) {
            extension.apkinfo = ApkInfo(extension, dependency.version ?: "pre-release")
        }
        val apkinfo = extension.apkinfo!!

        apkinfo.cache.mkdirs()

        if (!apkinfo.jarFile.exists()) {
            project.logger.lifecycle("Fetching JAR")

            val url = URL("${apkinfo.urlPrefix}/classes.jar")
            url.download(apkinfo.jarFile, createProgressLogger(project, "Download JAR"))
        }

        project.dependencies.add("compileOnly", project.files(apkinfo.jarFile))
    }
}