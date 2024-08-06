package com.example.androidcustomspinner


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcustomspinner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), CustomSpinner.OnSpinnerEventsListener {

    private lateinit var binding: ActivityMainBinding

    private val listOfSocials = listOf<Pair<Int, String>>(
        Pair(R.drawable.fb_messenger_ic, "Facebook"),
        Pair(R.drawable.discord_ic, "Discord"),
        Pair(R.drawable.snapchat_ic, "Snapchat"),
        Pair(R.drawable.whatsapp_ic, "WhatsApp")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CustomSpinnerAdapter(this, R.layout.spinner_item, listOfSocials)
        binding.spCustom.setSpinnerEventsListener(this)

        binding.spCustom.setAdapter(adapter)
    }

    override fun onPopupWindowOpened(spinner: Spinner?) {
        binding.spCustom.setBackgroundResource(R.drawable.custom_spinner_background_selected)
    }

    override fun onPopupWindowClosed(spinner: Spinner?) {
        binding.spCustom.setBackgroundResource(R.drawable.custom_spinner_background)
    }
}