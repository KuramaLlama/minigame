package lib.timers


class Countdown(private val startTime: Long,
                private val interval: Long,
                private val callback: TimeRunnable) : Runnable{

    private var time = startTime
    private var lastTimeTriggered = startTime
    private var timedOut = false

    private var onTimeOut: Runnable? = null

    fun onTimeout(onTimeOut: Runnable) {
       this.onTimeOut = onTimeOut
    }

    fun timedOut(): Boolean {
        return timedOut
    }

    override fun run() {
        if((time == lastTimeTriggered)&& time > 0L) {
            lastTimeTriggered = time - interval
            callback.run(time)
        } else if(time == 0L) {
            onTimeOut?.run()
            timedOut = true
        }
        time--
    }

    fun reset() {
        time = startTime
        lastTimeTriggered = startTime
    }
}