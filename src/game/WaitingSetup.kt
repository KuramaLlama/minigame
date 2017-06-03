package game

import listeners.playerjoin.ScoreboardUpdatePlayerCount
import listeners.playerjoin.PlayerJoinWaitingSetup
import gui.kit.KitSelectorGui
import gui.kit.controllers.SelectKitController
import kits.KitAssigner
import lib.gui.OpenInventoryGui
import lib.registers.EventsRegister
import scoreboard.PlayerScoreboardHandler

class WaitingSetup(private val eventsRegister: EventsRegister,
                   private val kitAssigner: KitAssigner,
                   private val scoreboardHandler: PlayerScoreboardHandler) {

    fun registerOnEvents() {
        eventsRegister.registerEvents(PlayerJoinWaitingSetup(), ScoreboardUpdatePlayerCount(scoreboardHandler))
    }

    fun registerGuis() {
        val kitSelectorGui = KitSelectorGui()
        kitSelectorGui.setKitIcons()
        val kitInventory = kitSelectorGui.build()

        eventsRegister.registerEvents(kitSelectorGui, OpenInventoryGui("Kit Selector", kitInventory),
                SelectKitController(kitInventory, scoreboardHandler, kitAssigner))
    }
}