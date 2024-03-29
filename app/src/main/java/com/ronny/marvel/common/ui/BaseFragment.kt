package com.ronny.marvel.common.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ronny.marvel.R
import com.ronny.marvel.databinding.CustomErrorDialogBinding

abstract class BaseFragment : Fragment() {

    var fragmentNavigatorExtras: FragmentNavigator.Extras = FragmentNavigatorExtras()

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

    open fun getExtras(): FragmentNavigator.Extras {
        return fragmentNavigatorExtras
    }

    fun alertDialogError(message: String, retry: () -> Unit) {
        Dialog(requireContext()).apply {
            setContentView(R.layout.custom_error_dialog)
            val tvMsg = this.findViewById<TextView>(R.id.msg_error)
            val btmRetry = this.findViewById<TextView>(R.id.btm_retry)
            tvMsg.text = message
            btmRetry.setOnClickListener {
                dismiss()
                retry()
            }
            show()
        }

    }
}