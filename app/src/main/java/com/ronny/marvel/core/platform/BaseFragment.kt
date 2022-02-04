package com.ronny.marvel.core.platform

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ronny.marvel.R
import com.ronny.marvel.AndroidApplication
import com.ronny.marvel.core.common.NavigationCommand
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.di.ApplicationComponent
import com.ronny.marvel.core.extensions.setupSnackBar


abstract class BaseFragment : Fragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
    }

    abstract fun getViewModel(): BaseViewModel

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.getNavigation().observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(
                        command.directions,
                        getExtras()
                    )
                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        })
    }

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
        ViewModelProvider(this@BaseFragment, this).get(T::class.java)

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    fun alertDialogError(message: String) {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(message)?.setTitle(R.string.dialog_error_title)
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }
}