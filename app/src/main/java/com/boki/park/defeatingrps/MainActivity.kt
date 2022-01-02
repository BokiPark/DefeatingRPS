package com.boki.park.defeatingrps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.boki.park.defeatingrps.databinding.ActivityMainBinding
import com.boki.park.defeatingrps.repo.RecordRepo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        RecordRepo.context = applicationContext
    }
}