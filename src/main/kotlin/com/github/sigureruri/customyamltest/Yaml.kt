package com.github.sigureruri.customyamltest

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class Yaml(
        val plugin: JavaPlugin,
        val fileName: String,
        val fromJar: Boolean
) {

    private val file: File = File(plugin.dataFolder, fileName)
    lateinit var yaml: YamlConfiguration

    init {
        saveDefault()
        reload()
    }

    fun saveDefault() {
        plugin.dataFolder.mkdir()
        if (!file.exists()) {
            if (fromJar) {
                plugin.saveResource(fileName, false)
            } else {
                file.createNewFile()
            }
        }
    }

    fun save() {
        try {
            yaml.save(file)
        } catch (e: IOException) {
            plugin.logger.warning("保存中に例外が発生しました...")
            e.printStackTrace()
        }
    }

    fun reload() {
        yaml = YamlConfiguration.loadConfiguration(file)

        val reader = InputStreamReader(
                plugin.getResource(fileName) ?: return,
                Charsets.UTF_8)

        yaml.setDefaults(
                YamlConfiguration.loadConfiguration(reader)
        )
    }

}