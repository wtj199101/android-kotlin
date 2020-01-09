package com.www.kotlin.ui.activity

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.base.kotlin.base.BaseActivity
import com.facebook.shimmer.ShimmerFrameLayout
import com.www.kotlin.App
import com.www.kotlin.R
import com.www.kotlin.utils.ValidateUtils
import com.www.kotlin.viewmodel.LoginRegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_head.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_register.*
import javax.inject.Inject

/**
 * 登录和注册页面activity
 */
class LoginRegisterActivity  : BaseActivity(),View.OnClickListener{


    override fun getContentView()= R.layout.activity_login

    private lateinit var loginShimmer: ShimmerFrameLayout

    private lateinit var registerShimmer: ShimmerFrameLayout

    private lateinit  var animatorOut: Animator
    private lateinit   var animatorIn:Animator

    @Inject
    lateinit var loginRegisterViewModel: LoginRegisterViewModel


    override fun init(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        loginShimmer= layout_login_shimmer
        registerShimmer= layout_register_shimmer
        loginShimmer.startShimmer()
        registerShimmer.startShimmer()

        setSupportActionBar(titlebar)
        titlebar.title="登录"
        titlebar.setNavigationOnClickListener {
           v->v.findNavController().popBackStack()
        }
        //注册登录和注册事件
        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)

        tv_to_login.setOnClickListener(this)
        tv_to_register.setOnClickListener(this)
        //设置动画
        setAnimators()

        //设置镜头距离

    }

    private fun setAnimators() {
          animatorOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip_out)
          animatorIn=AnimatorInflater.loadAnimator(this,R.animator.card_flip_in)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_login-> login()
            R.id.btn_register-> register()
            R.id.tv_to_register->filpCard(true)
            R.id.tv_to_login->filpCard(false)
        }
    }

    /**
     * true -> login to register
     * false ->  register to login
     */
    private fun filpCard(arrow:Boolean) {
        if(arrow){
            animatorOut.run {
                setTarget(layout_login)
                start()
            }
            animatorIn.run {
                setTarget(layout_register)
                start()
            }
            titlebar.title="注册"
        }else{
            animatorOut.run {
                setTarget(layout_register)
                start()
            }
            animatorIn.run {
                setTarget(layout_login)
                start()
            }
            titlebar.title="登录"
        }
    }

    private fun register() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun login() {
        if(ValidateUtils.validateEditText(login_username) && ValidateUtils.validateEditText(login_password)){
            loginRegisterViewModel
        }
    }

    override fun onResume() {
        super.onResume()
        loginShimmer.startShimmer()
        registerShimmer.startShimmer()
    }

    override fun onPause() {
        loginShimmer.stopShimmer()
        registerShimmer.stopShimmer()
        super.onPause()
    }


}