package org.exampl.nametag

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Team

class Nametag : JavaPlugin() {
    override fun onEnable() {
        logger.info("Nametag Plugin Enabled.")
    }

    fun setTeamColor(player: Player, teamName: String) {
        val scoreboard = Bukkit.getScoreboardManager()?.mainScoreboard ?: return

        val color = when (teamName.lowercase()) {
            "red" -> ChatColor.RED
            "blue" -> ChatColor.BLUE
            "green" -> ChatColor.GREEN
            "yellow" -> ChatColor.YELLOW
            else -> ChatColor.GRAY
        }

        val team = scoreboard.getTeam(teamName)
            ?: scoreboard.registerNewTeam(teamName).apply {
                prefix = color.toString()
                this.color = color
                setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS)
            }

        team.addEntry(player.name)
        player.scoreboard = scoreboard
        player.displayName = "${color}${player.name}${ChatColor.RESET}"
        player.playerListName = "${color}${player.name}${ChatColor.RESET}"
    }
}