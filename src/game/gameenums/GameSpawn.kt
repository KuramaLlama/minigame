package game.gameenums

enum class GameSpawn(private val x: Double,
                     private val y: Double,
                     private val z: Double,
                     private val yaw: Float,
                     private val pitch: Float) {

    SPAWN1(-41.5, 11.0, 41.5, 0f, 0f), SPAWN2(-29.5, 11.0, 47.5, 0f, 0f), SPAWN3(30.5, 11.0, 47.5, 0f, 0f),
    SPAWN4(43.5, 11.0, -42.5, 45f, 0f), SPAWN5(47.5, 11.0, -10.5, -135f, 0f), SPAWN6(55.5, 11.0, -54.5, 45f, 0f),
    SPAWN7(-54.5, 11.0, -54.5, -45f, 0f), SPAWN8(-51.5, 11.0, -5.5, 90f, 0f);

    fun getX(): Double {
        return x
    }

    fun getY(): Double {
        return y
    }

    fun getZ(): Double {
        return z
    }

    fun getYaw(): Float {
        return yaw
    }

    fun getPitch(): Float {
        return pitch
    }
}