package com.jo.architecturecomponentsapp

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData


class SpeedLiveData(private val car: Car) : LiveData<Int>(), Car.SpeedListener {

    override fun onActive() {
        super.onActive()
        Log.i("SpeedLiveData", "onActive")
        car.speedListener = this
    }

    override fun onSpeedChanged(newSpeed: Int) {
        value = newSpeed
    }
}

class Car {
    interface SpeedListener {
        fun onSpeedChanged(newSpeed: Int)
    }

    var speedListener : SpeedListener? = null

    fun startEngine() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({notifySpeed(10) }, 1000)
        handler.postDelayed({notifySpeed(50) }, 5000)
        handler.postDelayed({notifySpeed(100) }, 7000)
        handler.postDelayed({notifySpeed(2) }, 10000)

    }

    private fun notifySpeed(speed: Int) {
        Log.i("Car", "Speed changed to $speed")
        speedListener?.onSpeedChanged(speed)
    }
}