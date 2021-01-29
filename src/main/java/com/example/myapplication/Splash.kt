package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            Thread.sleep(3000)
            val intent = Intent(this, MainActivity :: class.java)
            startActivity(intent)
            finish()

        }catch(e: Exception){
            return;
        }

    }
}