package com.example.storage_test.acitivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.storage_test.R
import com.example.storage_test.utils.DateUtil
import com.example.storage_test.utils.Preference
import com.example.storage_test.utils.ViewUtil
import kotlinx.android.synthetic.main.activity_login_share.*
import kotlinx.android.synthetic.main.activity_share_read.*
import kotlinx.android.synthetic.main.activity_share_write.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class ShareWriteActivity : AppCompatActivity() {

    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var updateTime: String by Preference(this, "update_time", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_write)
        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            selector("请选择婚姻状态", types) { i ->
                tv_spinner.text = types[i]
                bMarried = if (i == 0) false else true
            }
        }
        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> toast("请先填写姓名")
                et_age.text.isEmpty() -> toast("请先填写年龄")
                et_height.text.isEmpty() -> toast("请先填写身高")
                et_weight.text.isEmpty() -> toast("请先填写体重")
                else -> {
                    name = et_name.text.toString()
                    age = et_age.text.toString().toInt()
                    height = et_height.text.toString().toLong()
                    weight = et_weight.text.toString().toFloat()
                    married = bMarried
                    updateTime = DateUtil.nowDateString
                    toast("数据已写入共享参数")
                }
            }
        }

    }
}

class ShareReadActivity : AppCompatActivity() {

    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var updateTime: String by Preference(this, "update_time", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_read)
        val  desc="共享参数中保存的信息如下：" +
                "\n name的取值为$name"+
                "\n age的取值为$age"+
                "\n height的取值为$height"+
                "\n weight的取值为$weight"+
                "\n married的取值为$married"+
                "\n updateTime的取值为$updateTime"
        tv_share.text=desc

    }
}

class LoginShareActivity : AppCompatActivity() {
    private var phone: String by Preference(this, "phone", "")
    private var password: String by Preference(this, "password", "")
    private val mRequestCode = 0
    private var bRemember = false
    private var mPassword = "111111"
    private var mVerifyCode: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_share)
        rg_login.setOnCheckedChangeListener { group, checkedId -> resetHint(checkedId) }
        ck_remember.setOnCheckedChangeListener { buttonView, isChecked -> bRemember = isChecked }
        et_phone.addTextChangedListener(HideTextWatcher(et_phone))
        et_password.addTextChangedListener(HideTextWatcher(et_password))
        btn_forget.setOnClickListener { doForget() }
        btn_login.setOnClickListener { doLogin() }
        et_phone.setText(phone)
        et_password.setText(password)
    }
    private fun resetHint(checkedId: Int) {
        if (checkedId == R.id.rb_password) {
            tv_password.text = "登录密码："
            et_password.hint = "请输入密码"
            btn_forget.text = "忘记密码"
            ck_remember.visibility = View.VISIBLE
        } else if (checkedId == R.id.rb_verifycode) {
            tv_password.text = "　验证码："
            et_password.hint = "请输入验证码"
            btn_forget.text = "获取验证码"
            ck_remember.visibility = View.INVISIBLE
        }
    }
    private inner class HideTextWatcher(private val mView: EditText) : TextWatcher {
        private val mMaxLength: Int = ViewUtil.getMaxLength(mView)
        private var mStr: CharSequence? = null

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mStr = s
        }

        override fun afterTextChanged(s: Editable) {
            if (mStr.isNullOrEmpty())
                return
            if (mStr!!.length == 11 && mMaxLength == 11 || mStr!!.length == 6 && mMaxLength == 6) {
                ViewUtil.hideOneInputMethod(this@LoginShareActivity, mView)
            }
        }
    }
    private fun doForget() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            toast("请输入正确的手机号")
            return
        }
        if (rb_password.isChecked) {
//            startActivityForResult<LoginForgetActivity>(mRequestCode, "phone" to phone)
        } else if (rb_verifycode.isChecked) {
            mVerifyCode = String.format("%06d", (Math.random() * 1000000 % 1000000).toInt())
            alert("手机号$phone，本次验证码是$mVerifyCode，请输入验证码", "请记住验证码") {
                positiveButton("好的") {  }
            }.show()
        }
    }
    private fun doLogin() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            toast("请输入正确的手机号")
            return
        }
        if (rb_password.isChecked) {
            if (et_password.text.toString() != mPassword) {
                toast("请输入正确的密码")
                return
            } else {
                loginSuccess()
            }
        } else if (rb_verifycode.isChecked) {
            if (et_password.text.toString() != mVerifyCode) {
                toast("请输入正确的验证码")
                return
            } else {
                loginSuccess()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == mRequestCode && data != null) {
            //用户密码已改为新密码
            mPassword = data.getStringExtra("new_password")
        }
    }

    //从修改密码页面返回登录页面，要清空密码的输入框
    override fun onRestart() {
        et_password.setText("")
        super.onRestart()
    }

    private fun loginSuccess() {
        alert("您的手机号码是${et_phone.text}，恭喜你通过登录验证，点击“确定”按钮返回上个页面", "登录成功") {
            positiveButton("确定返回") { finish() }
            negativeButton("我再看看") {  }
        }.show()
        if (bRemember) {
            phone = et_phone.text.toString()
            password = et_password.text.toString()
        }
    }


}