package com.www.kotlin.ui.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.*
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(val context: Context, @LayoutRes var rvItemId: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    SwipeRefreshLayout.OnRefreshListener {
    companion object {
        const val HEADER_VIEW = 1
        const val FOOTER_VIEW = 2
        const val EMPTY_VIEW = 3
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    /*** 列表数据 ***/
    protected lateinit var itemList: MutableList<T>

    /*** 头布局 ***/
    private lateinit var headerLayout: LinearLayout

    private var hasHeaderLayout: Boolean = false

    private var headerlayoutCount: Int = 1
    /*** 空布局 ****/
    private lateinit var emptyLayout: LinearLayout

    private var hasEmptyLayout: Boolean = false

    private var emptyLayoutCount: Int = 1
    /**
     *   获得列表项的个数
     */
    override fun getItemCount(): Int {
        if (hasEmptyLayout) {
            return 0
        }
        var count: Int = itemList.size
        count.also {
            if (hasHeaderLayout) {
                return it + headerlayoutCount
            }
        }
        return count
    }

    /**
     * 获取列表项类型
     */
    override fun getItemViewType(position: Int): Int {
        if (hasHeaderLayout && position == 0) {
            return HEADER_VIEW
        }
        if (hasEmptyLayout) {
            return EMPTY_VIEW
        }
        return super.getItemViewType(position)
    }

    //根据布局文件创建视图持有者，需要子类重写，实现增加不同布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val baseViewHolder: VH
        baseViewHolder = when (viewType) {
            HEADER_VIEW -> BaseViewHolder(headerLayout) as VH
            EMPTY_VIEW -> BaseViewHolder(emptyLayout) as VH
            else -> {
                val view = inflater.inflate(rvItemId, parent, false)
                BaseViewHolder(view) as VH
            }
        }
        return baseViewHolder
    }

    //绑定视图持有者中的各个控件对象，需要子类重写
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HEADER_VIEW) {
            return
        }
        if (getItemViewType(position) == EMPTY_VIEW) {
            return
        }
        onConvert(holder as BaseViewHolder, itemList[position])
    }


    protected abstract fun onConvert(holder: BaseViewHolder, t: T)

    override fun getItemId(position: Int) = position.toLong()

    /**
     * 设置数据
     */
    fun setNewData(newData: MutableList<T>) {
        itemList = newData
        notifyDataSetChanged()
    }

    /**
     * 新增数据
     */
    fun addData(addDataList: MutableList<T>) {
        itemList.addAll(addDataList)
        notifyItemRangeInserted(itemCount - addDataList.size, addDataList.size)
    }
}

open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val views: SparseArray<View> = SparseArray()
    fun <T : View> getView(@IdRes viewId: Int): T {
        val view = getViewOrNull<T>(viewId)
        checkNotNull(view) { "No view found with id $viewId" }
        return view
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
        val view = views.get(viewId)
        if (view == null) {
            itemView.findViewById<T>(viewId)?.let {
                views.put(viewId, it)
                return it
            }
        }
        return view as? T
    }

    fun <T : View> Int.findView(): T? {
        return itemView.findViewById(this)
    }

    open fun setText(@IdRes viewId: Int, value: CharSequence?): BaseViewHolder {
        getView<TextView>(viewId).text = value
        return this
    }

    open fun setText(@IdRes viewId: Int, @StringRes strId: Int): BaseViewHolder? {
        getView<TextView>(viewId).setText(strId)
        return this
    }

    open fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int): BaseViewHolder {
        getView<TextView>(viewId).setTextColor(color)
        return this
    }

    open fun setTextColorRes(@IdRes viewId: Int, @ColorRes colorRes: Int): BaseViewHolder {
        getView<TextView>(viewId).setTextColor(itemView.resources.getColor(colorRes))
        return this
    }

    open fun setImageResource(@IdRes viewId: Int, @DrawableRes imageResId: Int): BaseViewHolder {
        getView<ImageView>(viewId).setImageResource(imageResId)
        return this
    }

    open fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable?): BaseViewHolder {
        getView<ImageView>(viewId).setImageDrawable(drawable)
        return this
    }

    open fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap?): BaseViewHolder {
        getView<ImageView>(viewId).setImageBitmap(bitmap)
        return this
    }

    open fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int): BaseViewHolder {
        getView<View>(viewId).setBackgroundColor(color)
        return this
    }

    open fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes backgroundRes: Int): BaseViewHolder {
        getView<View>(viewId).setBackgroundResource(backgroundRes)
        return this
    }

    open fun setVisible(@IdRes viewId: Int, isVisible: Boolean): BaseViewHolder {
        val view = getView<View>(viewId)
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        return this
    }

    open fun setGone(@IdRes viewId: Int, isGone: Boolean): BaseViewHolder {
        val view = getView<View>(viewId)
        view.visibility = if (isGone) View.GONE else View.VISIBLE
        return this
    }

    open fun setEnabled(@IdRes viewId: Int, isEnabled: Boolean): BaseViewHolder {
        getView<View>(viewId).isEnabled = isEnabled
        return this
    }

}