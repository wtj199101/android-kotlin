package com.base.kotlin.http;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.github.leonardoxh.livedatacalladapter.Resource;

/**
 * Created by arvinljw on 2018/10/31 17:13
 * Function：
 * Desc：
 */
public abstract class BaseObserver<R> implements Observer<Resource<R>> {

    @Override
    public void onChanged(@Nullable Resource<R> response) {
        if (response != null) {
            R data = response.getResource();
            if (data != null) {
                callback(data);
            } else {
                if (response.getError() != null) {
                    onError(response.getError());
                }
            }
        }
    }

    public void onError(Throwable throwable) {
        Log.e("BaseObserver error",throwable.getMessage());
    }
    public abstract void callback(R response);
}
