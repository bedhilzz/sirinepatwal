package com.example.sirinepatwal

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.sirinepatwal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player: MediaPlayer? = null
    private var isHornPlayed: Boolean = false
    private var isWailPlayed: Boolean = false
    private var isYelpPlayed: Boolean = false
    private var isPhaserPlayed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initButton()
        setContentView(binding.root)
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initButton() {
//        binding.hornBtn.setOnClickListener { playHorn() }
        binding.hornBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    playHorn()
                MotionEvent.ACTION_UP -> {
                    isHornPlayed = false
                    stopPlayer()
                }
            }
            v?.onTouchEvent(event) ?: true
        }
//        binding.phsrBtn.setOnClickListener { playPhsr() }
        binding.phsrBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    playPhsr()
                MotionEvent.ACTION_UP -> {
                    isPhaserPlayed = false
                    stopPlayer()
                }
            }
            v?.onTouchEvent(event) ?: true
        }
//        binding.wailBtn.setOnClickListener { playWail() }
        binding.wailBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    playWail()
                MotionEvent.ACTION_UP -> {
                    isWailPlayed = false
                    Thread.sleep(100)
                    stopPlayer()
                }
            }
            v?.onTouchEvent(event) ?: true
        }
//        binding.yelpBtn.setOnClickListener { playYelp() }
        binding.yelpBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    playYelp()
                MotionEvent.ACTION_UP -> {
                    isYelpPlayed = false
                    stopPlayer()
                }
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.stopBtn.setOnClickListener { stopPlayer() }
    }

    private fun playHorn() {
        if (isHornPlayed) {
            stopPlayer()
        } else {
            isHornPlayed = true
            releasePlayer()
            player = MediaPlayer.create(this, R.raw.horn)
        }
        player!!.start()
    }

    private fun playPhsr() {
        if (isPhaserPlayed) {
            stopPlayer()
        } else {
            isPhaserPlayed = true
            releasePlayer()
            player = MediaPlayer.create(this, R.raw.phaser)
        }
        player!!.start()
    }

    private fun playWail() {
        if (isWailPlayed) {
            stopPlayer()
        } else {
            isWailPlayed = true
            releasePlayer()
            player = MediaPlayer.create(this, R.raw.wail)
        }
        player!!.start()
    }

    private fun playYelp() {
        if (isYelpPlayed) {
            stopPlayer()
        } else {
            isYelpPlayed = true
            releasePlayer()
            player = MediaPlayer.create(this, R.raw.yelp)
        }
        player!!.start()
    }

    private fun stopPlayer() {
        if (player != null) {
            player!!.stop()
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            player!!.release()
            player = null
        }
    }
}