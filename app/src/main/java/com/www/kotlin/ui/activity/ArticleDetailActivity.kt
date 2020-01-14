package com.www.kotlin.ui.activity

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.webkit.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.base.kotlin.base.BaseActivity
import com.www.kotlin.R
import com.www.kotlin.dao.entity.ArticleEntity
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.layout_head.*

class ArticleDetailActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {
    val KEY_TITLE = "title"
    val KEY_URL = "url"
    val KEY_ARTICLE = "item"
    private var url: String? = null
    private var articleEntity: ArticleEntity? = null

    override fun getContentView() = R.layout.activity_article_detail

    private lateinit var webView: WebView

    override fun init(savedInstanceState: Bundle?) {
        val title = intent.getStringExtra(KEY_TITLE)
        setSupportActionBar(titlebar)
        if(title.isNotEmpty()){
            titlebar.title=title
        }
        titlebar.setNavigationOnClickListener { onBackPressed() }
        detail_srl.setOnRefreshListener(this)
        webView = web_view
        initWebSettings()
        initWebClient()
        url=  intent.getStringExtra(KEY_URL)
        articleEntity= intent.getParcelableExtra<ArticleEntity>(KEY_ARTICLE)
        detail_srl.post {
            detail_srl.isRefreshing=true
            onRefresh()
        }

    }

    private fun initWebClient() {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                detail_srl.isRefreshing = false
            }
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler!!.proceed()
            }
        }
    }

    private fun initWebSettings() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true

        settings.setSupportZoom(false)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false

        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        settings.loadsImagesAutomatically = true
        settings.defaultTextEncodingName = "utf-8"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }

    override fun onRefresh() {
        webView.loadUrl(url)
    }

}

