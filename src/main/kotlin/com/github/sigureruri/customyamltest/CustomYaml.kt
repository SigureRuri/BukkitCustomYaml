package com.github.sigureruri.customyamltest

import org.bukkit.plugin.java.JavaPlugin

class CustomYaml : JavaPlugin() {

    lateinit var config : Yaml
    lateinit var config1: Yaml

    override fun onEnable() {
        dataFolder.mkdir()

        INSTANCE = this

        config = Yaml("config.yml", false)
        config1 = Yaml("config1.yml", true)
    }

    override fun onDisable() {
    }

    companion object {
        lateinit var INSTANCE: CustomYaml
            private set
    }
}
