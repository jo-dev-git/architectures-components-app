package com.jo.architecturecomponentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jo.architecturecomponentsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.helloTv.text = "Hello Jo"

        myLocationListener = MyLocationListener(lifecycle)
        lifecycle.addObserver(myLocationListener)
    }
}