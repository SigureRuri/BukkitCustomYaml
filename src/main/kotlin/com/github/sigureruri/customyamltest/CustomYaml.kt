package com.github.sigureruri.customyamltest

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class CustomYaml(
        val fileName: String,
        val fromJar: Boolean
) {

    private val INSTANCE = CustomYamlTest.INSTANCE
    private val file: File = File(INSTANCE.dataFolder, fileName)
    lateinit var yaml: YamlConfiguration

    init {
        saveDefault()
        reload()
    }

    fun saveDefault() {
        if (!file.exists()) {
            if (fromJar) {
                INSTANCE.saveResource(fileName, false)
            } else {
                file.createNewFile()
            }
        }
    }

    fun save() {
        try {
            yaml.save(file)
        } catch (e: IOException) {
            INSTANCE.logger.warning("保存中に例外が発生しました...")
            e.printStackTrace()
        }
    }

    fun reload() {
        yaml = YamlConfiguration.loadConfiguration(file)

        val reader = InputStreamReader(
                INSTANCE.getResource(fileName) ?: return,
                Charsets.UTF_8)

        yaml.setDefaults(
                YamlConfiguration.loadConfiguration(reader)
        )
    }

}