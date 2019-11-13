package com.example.complextest.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.complextest.R
import kotlinx.android.synthetic.main.activity_spinner_dialog.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class SpinnerDialogActivity : AppCompatActivity() {
    private val satellites= listOf("水星", "金星", "地球", "火星", "木星", "土星");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_dialog)
        sp_dialog.visibility= View.GONE
        tv_spanner.visibility=View.VISIBLE
        tv_spanner.text=satellites[0]
        tv_spanner.setOnClickListener{
            selector("请选择行星:",satellites){
                i ->   tv_spanner.text=satellites[i]
                toast("你选择的行星是${tv_spanner.text}")
            }
        }
    }
}
