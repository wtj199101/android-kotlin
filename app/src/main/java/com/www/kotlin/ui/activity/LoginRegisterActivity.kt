package com.www.kotlin.ui.activity

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.lifecycle.ViewModelProvider
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.R
import com.www.kotlin.dao.entity.LoginResultEntity
import com.www.kotlin.ui.lifecycle.ShimmerFrameLifeCycle
import com.www.kotlin.retrofit.response.ApiObserver
import com.www.kotlin.retrofit.response.Response
import com.www.kotlin.utils.Preference
import com.www.kotlin.utils.ValidateUtils
import com.www.kotlin.ui.viewmodel.LoginRegisterViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_head.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * 登录和注册页面activity
 */
class LoginRegisterActivity : BaseActivity(), View.OnClickListener, AnkoLogger {


    override fun getContentView() = R.layout.activity_login

    private lateinit var animatorOut: AnimatorSet

    private lateinit var animatorIn: AnimatorSet
    @Inject
    lateinit var factory: ViewModelProvider.Factory

   private val loginRegisterViewModel: LoginRegisterViewModel by viewModels { factory }

    private var login_card: Boolean = true

    private  var loginName by Preference(this,"loginName","")
    private  var appToken by Preference(this,"appToken","")
    private lateinit var loginPassword:String
    private   var registerName:String by Preference(this,"registerName","")
    private   var registerPassword:String  by Preference(this,"registerPassword","")
    private   var confirmPassword:String  by Preference(this,"confirmPassword","")


    override fun init(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        lifecycle.run {
            addObserver(ShimmerFrameLifeCycle(lifecycle,layout_login_shimmer))
            addObserver(ShimmerFrameLifeCycle(lifecycle,layout_register_shimmer))
        }
        setSupportActionBar(titlebar)
        titlebar.title = "登录"
        titlebar.setNavigationOnClickListener {
            super.onBackPressed()
        }
        login_username.setText(loginName)
        //注册登录和注册事件
        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)

        tv_to_login.setOnClickListener(this)
        tv_to_register.setOnClickListener(this)
        //设置动画
        setAnimators()
        //设置镜头距离
        setCameraDistance()
    }

    private fun setCameraDistance() {
        val distance = 16000
        val scale = resources.displayMetrics.density * distance
        layout_login.cameraDistance = scale
        layout_register.cameraDistance = scale
    }

    private fun setAnimators() {
        animatorOut = AnimatorSet().apply {
            play(
                AnimatorInflater.loadAnimator(
                    this@LoginRegisterActivity,
                    R.animator.card_flip_out
                )
            )
            doOnStart {
                layout_register.visibility = View.VISIBLE
                layout_login.visibility = View.VISIBLE
            }
        }
        animatorIn = AnimatorSet().apply {
            play(AnimatorInflater.loadAnimator(this@LoginRegisterActivity, R.animator.card_flip_in))
            doOnEnd {
                it.doOnEnd {
                    if (login_card) {
                        layout_register.visibility = View.VISIBLE
                        layout_login.visibility = View.GONE
                    } else {
                        layout_login.visibility = View.VISIBLE
                        layout_register.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login -> login()
            R.id.btn_register -> register()
            R.id.tv_to_register -> filpCard(true)
            R.id.tv_to_login -> filpCard(false)
        }
    }

    /**
     * true -> login to register
     * false ->  register to login
     */
    private fun filpCard(arrow: Boolean) {
        if (arrow) {
            login_card = arrow
            animatorOut.run {
                animatorOut.setTarget(layout_login)
                start()
            }
            animatorIn.run {
                animatorIn.setTarget(layout_register)
                start()
            }
            titlebar.title = "注册"
        } else {
            login_card = arrow
            animatorOut.run {
                animatorOut.setTarget(layout_register)
                start()
            }
            animatorIn.run {
                animatorIn.setTarget(layout_login)
                start()
            }
            titlebar.title = "登录"
        }
    }

    private fun register() {
        if(ValidateUtils.validateEditText(register_username)
            &&ValidateUtils.validateEditText(register_password)
            &&ValidateUtils.validateEditText(confirm_password)){
            registerName = register_username.text.toString()
            registerPassword=  register_password.text.toString()
            confirmPassword=  confirm_password.text.toString()
            if(registerPassword!=confirmPassword){
                toast("密码不一致！")
                //这边一般是加监听器，这边 TODO
                return
            }
            loginRegisterViewModel.registerUser(registerName,registerPassword).observe(this,object :ApiObserver<LoginResultEntity>(){
                override fun onSuccess(response: Response<LoginResultEntity>?) {
                    toast("注册成功")

                    onBackPressed()
                }
                override fun onError(code: Int, msg: String?) {
                    super.onError(code, msg)
                    if (msg != null) {
                        toast(msg)
                    }
                }
            })
        }

    }

    private fun login() {
        if (ValidateUtils.validateEditText(login_username) && ValidateUtils.validateEditText( login_password )  ) {
            loginRegisterViewModel.loginUser(login_username.text.toString(),login_password.text.toString()).observe(this,
               object: ApiObserver<LoginResultEntity>() {
                   override fun onSuccess(response: Response<LoginResultEntity>?) {
                       loginName=login_username.text.toString()
                       loginPassword=login_password.text.toString()
                        toast("登录成功")
                       appToken=registerName
                       onBackPressed()
                   }
                   override fun onError(code: Int, msg: String?) {
                       super.onError(code, msg)
                       if (msg != null) {
                           toast(msg)
                       }
                   }

               }
            )
        }
    }

}