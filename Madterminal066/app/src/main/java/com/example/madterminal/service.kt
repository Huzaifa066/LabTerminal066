package com.example.recyleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.services.MyService
import kotlinx.android.synthetic.main.activity_servic.*

class servic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servic)
        btnst.setOnClickListener{
            Intent(this,MyService::class.java).also {
                startService(it)
                txt1.text="Services is Running"
            }
        }
        
    }
}