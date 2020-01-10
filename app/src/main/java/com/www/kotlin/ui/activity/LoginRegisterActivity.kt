package com.www.kotlin.ui.activity

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.base.kotlin.base.BaseActivity
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.animation.AnimatorSetCompat
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.dao.entity.LoginResultEntity
import com.www.kotlin.lifecycle.ShimmerFrameLifeCycle
import com.www.kotlin.retrofit.response.ApiObserver
import com.www.kotlin.retrofit.response.Response
import com.www.kotlin.utils.ValidateUtils
import com.www.kotlin.viewmodel.LoginRegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.layout_head.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * 登录和注册页面activity
 */
class LoginRegisterActivity : BaseActivity(), View.OnClickListener, AnkoLogger {


    override fun getContentView() = R.layout.activity_login


    private lateinit var registerShimmer: ShimmerFrameLayout

    private lateinit var animatorOut: AnimatorSet
    private lateinit var animatorIn: AnimatorSet

    @Inject
    lateinit var loginRegisterViewModel: LoginRegisterViewModel


    private var login_card: Boolean = true


    override fun init(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        ShimmerFrameLifeCycle(applicationContext,lifecycle,layout_login_shimmer)
        ShimmerFrameLifeCycle(applicationContext,lifecycle,layout_register_shimmer)

        setSupportActionBar(titlebar)
        titlebar.title = "登录"
        titlebar.setNavigationOnClickListener {
            super.onBackPressed()
        }
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun login() {
        if (ValidateUtils.validateEditText(login_username) && ValidateUtils.validateEditText(
                login_password
            )
        ) {
            info ("11111" )
            loginRegisterViewModel.loginUser(login_username.text.toString(),login_password.text.toString()).observe(this,
               object: ApiObserver<LoginResultEntity>() {
                   override fun onSuccess(response: Response<LoginResultEntity>?) {
                      var  entity:LoginResultEntity= response!!.data
                       info ( "loginUser=$entity" )
                       entity.password=login_password.text.toString()
                   }

               }
            )
        }
    }

}