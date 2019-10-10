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

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size == 2) {
            val subCommand = if (args.isEmpty()) "" else args[0]
            val subCommand1 = if (args.size <= 1) "" else args[1]
            config1.yaml.set(subCommand, subCommand1)
            config1.save()
            sender.sendMessage("書き込み...")
        }

        return true
    }

    companion object {
        lateinit var INSTANCE: CustomYamlTest
            private set
    }
}
