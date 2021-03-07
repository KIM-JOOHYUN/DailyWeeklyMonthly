package com.example.myapplication

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            Thread.sleep(3000)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }catch (e: Exception){
            return;
        }

    }
}
