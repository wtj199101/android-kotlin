package com.example.myapplication2

import android.os.Bundle
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)
        val view = findViewById(R.id.forecast_list) as AbsListView
    }
}
