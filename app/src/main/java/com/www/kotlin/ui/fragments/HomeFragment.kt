package com.www.kotlin.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.base.kotlin.base.BaseFragment
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.www.kotlin.R
import com.www.kotlin.dao.entity.ArticleEntity
import com.www.kotlin.dao.entity.BannerEntity
import com.www.kotlin.dao.entity.PageList
import com.www.kotlin.retrofit.response.ApiObserver
import com.www.kotlin.retrofit.response.Response
import com.www.kotlin.ui.activity.ArticleDetailActivity
import com.www.kotlin.ui.adapters.ArticleQuickAdapter
import com.www.kotlin.ui.viewmodel.HomeViewModel
import com.www.kotlin.utils.ImageLoadUtils
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    @Inject
    lateinit var appViewModeFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels {
        appViewModeFactory
    }
    private lateinit var adapter: ArticleQuickAdapter

    override fun init(savedInstanceState: Bundle?) {
        adapter = ArticleQuickAdapter(home_srl,home_recycler,R.layout.item_home_article)
        //设置view 头部
        initBanner()
        loadItmeList(adapter.firstPage)
        adapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.getItem(position)
            startActivity(intentFor<ArticleDetailActivity>("item" to item))
        }
        adapter.autoRefresh()
        adapter.loadMoreModule!!.setOnLoadMoreListener {object : OnLoadMoreListener{
            override fun onLoadMore() {
                loadItmeList(adapter.currPage)
                adapter.autoRefresh()
            }
        }

        }
    }

    private fun loadItmeList(page:Int) {
        //初始化 datas
        homeViewModel.getIndexArticles(page)
            .observe(this, object : ApiObserver<PageList<ArticleEntity>>() {
                override fun onSuccess(response: Response<PageList<ArticleEntity>>?) {
                    if (response!!.data != null) {
                        val dataList = response.data
                        adapter.currPage = dataList.curPage
                        adapter.itemList=dataList.data
                    }
                }
            })
    }



    private fun initBanner() {
        homeViewModel.getBanners().observe(this, object : ApiObserver<List<BannerEntity>>() {
            override fun onSuccess(bannerList: Response<List<BannerEntity>>?) {
                val bannerData = bannerList!!.data
                val bannerView = View.inflate(activity, R.layout.layout_banner, null)
                var banner = bannerView.find<Banner>(R.id.banner)
                banner.setImageLoader(object : ImageLoader() {
                    override fun displayImage(
                        context: Context?,
                        path: Any?,
                        imageView: ImageView?
                    ) = ImageLoadUtils.load(context!!, path!!, imageView!!, false, 0)
                })
                banner.setDelayTime(5000)
                banner.setOnBannerListener {
                    val entity = bannerData[it]
                    startActivity<ArticleDetailActivity>(
                        "title" to entity.title,
                        "url" to entity.url
                    )
                }
                banner.setImages(BannerEntity.toImages(bannerData))
                banner.start()
                adapter.setHeaderView(bannerView)
            }
        }
        )
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun getContentView() = R.layout.fragment_home

}



