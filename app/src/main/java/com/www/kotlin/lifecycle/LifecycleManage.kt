package com.www.kotlin.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerFrameLifeCycle constructor(lifecycle: Lifecycle,shimmerFrameLayout: ShimmerFrameLayout):LifecycleObserver{
    private  var lifecycle: Lifecycle=lifecycle
    private  var shimmerFrameLayout: ShimmerFrameLayout=shimmerFrameLayout

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume(){
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)){
            shimmerFrameLayout.startShimmer()
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause(){
            shimmerFrameLayout.stopShimmer()
    }
}