package com.www.kotlin.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.base.kotlin.base.BaseFragment
import com.base.kotlin.widget.MultiStatusViewHelper
import com.www.kotlin.R
import com.www.kotlin.dao.entity.TreeEntity
import com.www.kotlin.retrofit.response.ApiObserver
import com.www.kotlin.retrofit.response.Response
import com.www.kotlin.ui.activity.KnowledgeTreeTabActivity
import com.www.kotlin.ui.adapters.KnowledgeQuickAdapter
import com.www.kotlin.ui.viewmodel.HomeViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_knowledge.*
import kotlinx.android.synthetic.main.layout_multi_status_view.*
import javax.inject.Inject

class KnowledgeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    override fun getContentView() = R.layout.fragment_knowledge
    @Inject
    lateinit var homeViewModel: HomeViewModel

    private lateinit var viewHelper: MultiStatusViewHelper
    private lateinit var adapter: KnowledgeQuickAdapter

    override fun init(savedInstanceState: Bundle?) {
        viewHelper = MultiStatusViewHelper(multi_status_view, true)

        knowledge_rv.layoutManager = LinearLayoutManager(context)
        adapter = KnowledgeQuickAdapter(R.layout.item_knowledge_tree)
        knowledge_rv.adapter = adapter
        adapter.setOnItemClickListener { adapter2, view, position ->
            val item:  TreeEntity = adapter.getItem(position)!!
            val intent = Intent(context, KnowledgeTreeTabActivity::class.java)
            intent.putExtra("data", item)
            intent.putExtra("position", 0)
            startActivity(intent)
        }
        adapter.setEmptyView(R.layout.msv_layout_empty)

        knowledge_rv.post {
            onRefresh()
        }

    }

    override fun onRefresh() {
        homeViewModel.getKnowledgeTree().observe(this,object :ApiObserver<List<TreeEntity>>(){
            override fun onSuccess(response: Response<List<TreeEntity>>?) {
                if(response!=null && response.data.isNotEmpty() ){
                    adapter.addData(response.data)
                }
            }

            override fun onFailure(code: Int, msg: String?) {
                super.onFailure(code, msg)
                viewHelper.showRetryInList(MultiStatusViewHelper.hasData(adapter.data))
            }

        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}