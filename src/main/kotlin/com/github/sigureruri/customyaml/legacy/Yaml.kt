package com.github.sigureruri.customyaml.legacy

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

/**
 * dataFolder内にyamlファイルを配置し操作する
 *
 * @author SigureRuri
 *
 * @param plugin yamlファイルを配置するdataFolderを所有するJavaPluginクラス
 * @param fileName 作成するyamlファイルの名称
 * @param fromJar yamlファイルをjarファイル内から保存するかどうか
 */

@Deprecated("")
open class Yaml(
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
        val dataFolder = plugin.dataFolder
        if (!dataFolder.exists() || !dataFolder.isDirectory) {
            dataFolder.mkdir()
        }
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
            plugin.logger.warning("An exception occurrence while saving $fileName...")
            e.printStackTrace()
        }
    }

    fun reload() {
        yaml = YamlConfiguration.loadConfiguration(file)

        val reader = InputStreamReader(
                plugin.getResource(fileName) ?: return,
                Charsets.UTF_8
        )

        yaml.setDefaults(
                YamlConfiguration.loadConfiguration(reader)
        )
    }

}