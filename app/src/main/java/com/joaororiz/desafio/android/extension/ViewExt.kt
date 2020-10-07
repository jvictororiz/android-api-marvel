package com.joaororiz.desafio.android.extension

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joaororiz.desafio.android.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.setImageFromUrl(
    url: String?, @DrawableRes idImage: Int? = null,
    callbackSucess: (() -> Unit)? = null,
    callbackError: (() -> Unit)? = null,
    callbackFinish: (() -> Unit)? = null
) {
    Picasso.get()
        .load(url)
        .centerCrop()
        .apply { idImage?.let { placeholder(idImage).error(it) } }
        .fit()
        .into(this, object : Callback {
            override fun onSuccess() {
                callbackSucess?.invoke()
                callbackFinish?.invoke()
            }

            override fun onError(e: Exception?) {
                callbackError?.invoke()
                callbackFinish?.invoke()

            }
        })
}

fun RecyclerView.listenerEnd(onEnd: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                onEnd.invoke()
            }
        }
    })
}