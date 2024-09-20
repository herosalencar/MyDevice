package com.example.mydevice

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var myDevice: TextView
    private lateinit var manufacturer: TextView
    private lateinit var model: TextView
    private lateinit var versionOS: TextView
    private lateinit var nivelAPI: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDevice = findViewById(R.id.TextMyDevice)
        manufacturer = findViewById(R.id.TextManufacturer)
        model = findViewById(R.id.TextModel)
        versionOS = findViewById(R.id.TextVersionAndroid)
        nivelAPI = findViewById(R.id.TextAPINivel)

        myDevice.text = Build.DEVICE
        manufacturer.text = Build.MANUFACTURER
        model.text = Build.MODEL
        versionOS.text = "Android ${Build.VERSION.RELEASE}"
        nivelAPI.text = "API ${Build.VERSION.SDK_INT}"

    }
}