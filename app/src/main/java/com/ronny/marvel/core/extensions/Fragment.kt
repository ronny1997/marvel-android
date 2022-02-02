package com.ronny.marvel.core.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ronny.marvel.core.util.Event

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

fun Fragment.showSnackBar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_LONG) {
        activity.getIfActive()?.let {
                Snackbar.make(it.findViewById<View>(android.R.id.content), snackbarText, timeLength).show()
        }
}

fun Fragment.setupSnackBar(
        lifecycleOwner: LifecycleOwner,
        snackbarEvent: LiveData<Event<String>>,
        timeLength: Int = Snackbar.LENGTH_LONG
) {
        snackbarEvent.observe(lifecycleOwner, Observer { event ->
                event.getContentIfNotHandled()?.let { text ->
                        context?.let { showSnackBar(text, timeLength) }
                }
        })
}