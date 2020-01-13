package com.www.kotlin.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.base.kotlin.base.BaseFragment
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
import com.youth.banner.loader.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class HomeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: ArticleQuickAdapter
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onRefresh() {
        homeViewModel.getIndexArticles(adapter.currPage)
            .observe(this, object : ApiObserver<PageList<ArticleEntity>>() {
                override fun onSuccess(response: Response<PageList<ArticleEntity>>?) {
                    if(response!!.data!=null){
                        val dataList = response.data
                        adapter.currPage=dataList.curPage
                        adapter.addData(dataList.data)
                    }

                }
            })
    }

    override fun init(savedInstanceState: Bundle?) {
        home_recycler.layoutManager = LinearLayoutManager(context)
         adapter = ArticleQuickAdapter(home_recycler, home_srl)
        addBanner()
        home_srl.post {
            onRefresh()
        }
        adapter.setOnItemClickListener { adapter, view, position ->
                val item = adapter.getItem(position)
            startActivity(intentFor<ArticleDetailActivity>("item" to item))
        }
        home_recycler.itemAnimator = DefaultItemAnimator()
    }

    private fun addBanner() {
        homeViewModel.getBanners().observe(this,object:ApiObserver<List<BannerEntity>>() {
                override fun onSuccess(bannerList: Response<List<BannerEntity>>?) {
                    val bannerData = bannerList!!.data
                    banner.setImageLoader(object: ImageLoader(){
                    override fun displayImage(
                        context: Context?,
                        path: Any?,
                        imageView: ImageView?
                    ) = ImageLoadUtils.load(context!!, path!!, imageView!!, false, 0)
                })
                    banner.setDelayTime(5000)
                     banner.setOnBannerListener {
                        val entity = bannerData[it]
                        startActivity<ArticleDetailActivity>("title" to entity.title,"url" to entity.url)
                    }
                    banner.setImages(BannerEntity.toImages(bannerData))
                    banner.start()
                    adapter.addHeaderView(banner)
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

