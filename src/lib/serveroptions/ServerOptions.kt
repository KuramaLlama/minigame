package lib.serveroptions


enum class ServerOptions(private val serverOption: ServerOption) {
    BLOCKEXPLODE(BlockExplodeOption()), BUILDOPTION(BuildOption()), DAMAGEOPTION(DamageOption()),
    DROPITEMONDEATHOPTION(DropItemOnDeathOption()), HUNGEROPTION(HungerOption()),
    ITEMDROPOPTION(ItemDropOption()), MUTABLEINVENTORYOPTION(MutableInventoryOption()), WEATHERCHANGEOPTION(WeatherChangeOption());

    fun getServerOption(): ServerOption {
        return serverOption
    }

    fun toggle(): Boolean {
        serverOption.setDisabled(!serverOption.getDisabled())
        return serverOption.getDisabled()
    }
}