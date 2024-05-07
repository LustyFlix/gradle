package com.lustyflix.streamverse.gradle.entities

data class PluginManifest(
    val pluginClassName: String?,
    val name: String,
    val version: Int,
    val requiresResources: Boolean
)