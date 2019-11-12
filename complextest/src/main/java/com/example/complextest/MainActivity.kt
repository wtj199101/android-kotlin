package com.example.complextest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complextest.activity.SpinnerDialogActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_spinner_dialog.setOnClickListener { startActivity<SpinnerDialogActivity>() }
    }
}
