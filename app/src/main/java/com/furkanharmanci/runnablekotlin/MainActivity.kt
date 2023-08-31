package com.furkanharmanci.runnablekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.furkanharmanci.runnablekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var number : Int = 0
    private var runnable : Runnable = Runnable {}
    private var handler : Handler = Handler(Looper.getMainLooper())
    private lateinit var result : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.stop.isEnabled = false
    }

    fun start(view : View) {
        number = 0

        runnable = Runnable {
            number++
            result = "Time: $number"
            binding.timer.text = result
            handler.postDelayed(runnable, 1000)
        }
        handler.post(runnable)
        binding.start.isEnabled = false
        binding.stop.isEnabled = true
    }

    fun stop(view : View) {
        binding.start.isEnabled = true
        binding.stop.isEnabled = false
        number = 0
        result = "The Counter Stopped!"
        binding.timer.text = result
        handler.removeCallbacks(runnable)
    }
}