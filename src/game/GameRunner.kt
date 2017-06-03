package game

import game.countdown.BroadcastCountdown
import game.gameenums.GameState
import game.ingame.InGameRunner
import lib.timers.Countdown
import org.bukkit.scheduler.BukkitRunnable


class GameRunner(private val gameSetup: GameSetup): BukkitRunnable() { //a game runner should not be
    //a runnable. It should take runnables but not be a runnable.
    //here is where we have the game loop where it actually runs the game
    //and executes runnables accordingly

    private val startCountdown = Countdown((30 * 20).toLong(), 20, BroadcastCountdown())
    private val pregameCountdown = Countdown((10 * 20).toLong(), 20, BroadcastCountdown())
    private val inGameRunner = InGameRunner()

    private var state = GameState.WAITING
    private var ticks: Long = 0

    fun getGameState(): GameState {
        return state
    }

    fun setGameState(state: GameState) {
        this.state = state
    }

    override fun run() {
        ++ticks

        when(state) {
            GameState.WAITING ->
                startCountdown.reset()
            GameState.COUNTDOWN ->
                countdown(startCountdown, Runnable{ gameSetup.spawnOnlinePlayersInGame() }, GameState.PREGAME)
            GameState.PREGAME ->
                countdown(pregameCountdown, Runnable{ setupGame() }, GameState.INGAME)
            GameState.INGAME ->
                startGame()
        }
    }

    private fun countdown(countdown: Countdown, onTimeout: Runnable, gameState: GameState) {
        countdown.run()
        countdown.onTimeout(onTimeout)

        if(countdown.timedOut())
            state = gameState
    }

    private fun setupGame() {
        gameSetup.givePlayersKits()
        gameSetup.setServerOptions()
        gameSetup.registerEvents()
        gameSetup.registerGuis()
    }

    private fun startGame() {
        inGameRunner.run()
    }
}

