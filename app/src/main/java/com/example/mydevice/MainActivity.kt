package com.example.mydevice

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var myDevice: TextView
    private lateinit var manufacturer: TextView
    private lateinit var model: TextView
    private lateinit var versionOS: TextView
    private lateinit var nivelAPI: TextView
    private lateinit var nameCPU: TextView
    private lateinit var architecture: TextView
    private lateinit var memoryTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDevice = findViewById(R.id.TextMyDevice)
        manufacturer = findViewById(R.id.TextManufacturer)
        model = findViewById(R.id.TextModel)
        versionOS = findViewById(R.id.TextVersionAndroid)
        nivelAPI = findViewById(R.id.TextAPINivel)
        nameCPU = findViewById(R.id.TextInfoNameCPU)
        architecture = findViewById(R.id.TextInfoArchitecture)
        memoryTotal = findViewById(R.id.TextInfoMemory)

        // CardView Android OS

        // Nome definido pelo usuário nas configurações (Settings > Sobre o Telefone)
        val deviceNameFromSettings = Settings.Global.getString(contentResolver, "device_name")
        val fallbackDeviceName = if (deviceNameFromSettings.isNullOrEmpty()) {
            // Se não houver um nome de dispositivo, use o fabricante e modelo como fallback
            "${Build.MANUFACTURER.uppercase()} ${Build.MODEL}"
        } else {
            deviceNameFromSettings
        }

        myDevice.text = fallbackDeviceName
        manufacturer.text = Build.MANUFACTURER.replaceFirstChar { it.uppercase() }
        model.text = Build.MODEL
        versionOS.text = "Android ${Build.VERSION.RELEASE}"
        nivelAPI.text = "API ${Build.VERSION.SDK_INT}"

        // CardView Hardware
        // Nome Processador
        nameCPU.text = "CPU: ${Build.HARDWARE.uppercase()}" // ou ${Build.BOARD}

        // Arquitetura
        architecture.text = "Arquitetura: ${Build.SUPPORTED_ABIS[0]}"

        // Memória
        memoryTotal.text = "Total de memória: ${memoryDevice()} GB"

    }

    private fun memoryDevice(): String {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        val memoryTotal = memoryInfo.totalMem / Math.pow(1024.0, 3.0).roundToInt()

        return memoryTotal.toString()
    }
}