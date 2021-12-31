package com.example.madterminal

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services.MyService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_servic.*
import kotlinx.android.synthetic.main.new_layout.*

private var Nothing?.layoutManager: LinearLayoutManager
    get() {}
    set() {}

class MainActivity : AppCompatActivity() {

    var edi :String = "comsats"
    var edi2:String="welcome"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, MyService::class.java).also {
            startService(it)
            Toast.makeText(this,"Services is running ",Toast.LENGTH_LONG).show()
        }


        val recycle = null
        recycle.layoutManager=LinearLayoutManager(this)
        recycle.adapter= Adapter()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                111
            )
        }
        else{
            reciveMsg()
        }
        msg_button.setOnClickListener{
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(edi,"ME",edi2,null,null)
        }
        msg_button.setOnClickListener{
            val intent = Intent(this,service::class.java)
            startActivity(intent)
        }


    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            reciveMsg()
        }
    }

    private fun reciveMsg() {
        val br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                        Toast.makeText(applicationContext,sms.displayMessageBody,Toast.LENGTH_LONG).show()

                    }
                }
            }

        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }
}