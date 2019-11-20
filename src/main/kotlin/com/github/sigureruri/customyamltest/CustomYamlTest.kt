package com.github.sigureruri.customyamltest

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class CustomYamlTest : JavaPlugin() {

    lateinit var config : CustomYaml
    lateinit var config1: CustomYaml

    override fun onEnable() {
        dataFolder.mkdir()

        INSTANCE = this

        config = CustomYaml("config.yml", false)
        config1 = CustomYaml("config1.yml", true)
    }

    override fun onDisable() {
    }

    companion object {
        lateinit var INSTANCE: CustomYamlTest
            private set
    }
}
