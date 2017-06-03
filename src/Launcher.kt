import game.WaitingSetup
import game.GameHandler
import game.GameRunner
import game.GameSetup
import server.ServerSetup
import kits.Kit
import kits.KitAssigner
import lib.messages.scoreboard.ScoreboardBuilder
import lib.registers.CommandsRegister
import lib.registers.EventsRegister
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import scoreboard.PlayerScoreboardHandler
import java.util.*

class Launcher : JavaPlugin() {

    private val playerKitMap = HashMap<Player, Kit>()
    private val playerScoreboardMap = HashMap<Player, ScoreboardBuilder>()

    private val commandsRegister = CommandsRegister(this)
    private val eventsRegister = EventsRegister(this)

    private val serverSetup = ServerSetup(this, commandsRegister, eventsRegister)
    private val waitingSetup = WaitingSetup(eventsRegister, KitAssigner(playerKitMap), PlayerScoreboardHandler(playerScoreboardMap))
    private val gameSetup = GameSetup(this, eventsRegister, playerKitMap)
    private val gameRunner = GameRunner(gameSetup)

    override fun onEnable() {
        initServerSetup()
        initWaitingSetup()
        runGame()
    }

    private fun initServerSetup() {
        serverSetup.setupWorlds()
        serverSetup.registerCommands()
        serverSetup.registerOptions()
        serverSetup.registerEvents()
    }

    private fun initWaitingSetup() {
        waitingSetup.registerOnEvents()
        waitingSetup.registerGuis()
    }

    private fun runGame() {
        gameRunner.runTaskTimer(this, 0L, 1L)//
        eventsRegister.registerEvents(GameHandler(gameRunner))
    }
}