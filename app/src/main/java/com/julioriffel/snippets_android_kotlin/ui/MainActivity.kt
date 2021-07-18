/*
 * Copyright (c) 2021. Julio Cezar Riffel<julioriffel@gmail.com>
 */

package com.julioriffel.snippets_android_kotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.julioriffel.snippets_android_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btPermissoes.setOnClickListener { startPermissoes() }
        binding.btBarcode.setOnClickListener { startPermissoes() }
    }

    private fun startPermissoes() {
        startActivity(
            Intent(
                this,
                PermissoesActivity::class.java
            )
        )
    }
}