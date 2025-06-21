package com.jo.architecturecomponentsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jo.architecturecomponentsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener
    private lateinit var binding: ActivityMainBinding
    private lateinit var speedLiveData: SpeedLiveData
    private lateinit var lengthLiveData : LiveData<Int>

    private var nameIndex = 0
    private val nameLivedata = MutableLiveData<String>()

    private val counter = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        counter.value = 0

        myLocationListener = MyLocationListener(lifecycle)
        lifecycle.addObserver(myLocationListener)

        binding.button.setOnClickListener {
            nameLivedata.value = when (nameIndex) {
                0 -> "Bob"
                1 -> "Jo"
                2 -> "jonathan"
                else -> "Who ?"
            }
            Log.i("MainActivity", "value = ${nameLivedata.value}")
            nameIndex = (nameIndex + 1) % 3
        }


        val observer: Observer<Int> = Observer { newValue ->
            binding.speedTv.text = buildString {
                append("Speed = ")
                append(newValue.toString())
            }
        }

        val car = Car()
        speedLiveData = SpeedLiveData(car)
        speedLiveData.observe(this, observer)
        car.startEngine()
    }
}