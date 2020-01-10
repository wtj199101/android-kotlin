package com.www.kotlin.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.facebook.shimmer.ShimmerFrameLayout
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ShimmerFrameLifeCycle constructor(context: Context,lifecycle: Lifecycle,shimmerFrameLayout: ShimmerFrameLayout):AnkoLogger{
    private var context:Context=context
    private  var lifecycle: Lifecycle=lifecycle
    private  var shimmerFrameLayout: ShimmerFrameLayout=shimmerFrameLayout

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume(){
        info ("11122211"+context.packageName )
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)){
            shimmerFrameLayout.startShimmer()
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause(){
        info ("11111"+context.packageName )
            shimmerFrameLayout.stopShimmer()
    }
}