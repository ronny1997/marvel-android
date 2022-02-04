package com.ronny.marvel.features.characters.characterslist

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.lifecycleScope
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.databinding.FragmentCharactersListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharactersListFragment : BaseFragment() {

    private lateinit var binding: FragmentCharactersListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CharactersListViewModel>

    private val charactersListViewModel: CharactersListViewModel by lazy { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        binding.viewModel = charactersListViewModel
        animations(container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            charactersListViewModel.charactersUiState.collect { charactersUiState ->
                if (charactersUiState.isLoading) {
                }
                if (charactersUiState.error.isNotBlank()) {
                    binding.prbCharacters.visibility = View.GONE
                }
                charactersUiState.charactersListDto?.let {
                    binding.prbCharacters.visibility = View.GONE
                    binding.character = it
                }
            }
        }
        initListeners()
    }

    private fun initListeners() {
        binding.gvCharactersList.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
            }

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                val lastVisiblePosition = binding.gvCharactersList.lastVisiblePosition + 1
                if (lastVisiblePosition != 0 && lastVisiblePosition == totalItemCount && binding.prbCharacters.visibility == View.GONE) {
                    binding.prbCharacters.visibility = View.VISIBLE
                    charactersListViewModel.lastVisibility.value = totalItemCount
                }
            }

        })
    }

    private fun animations(parent: ViewGroup?) {
        parent?.let {
            val transition: Transition = Slide(Gravity.BOTTOM)
            transition.duration = 600
            transition.addTarget(binding.prbCharacters)
            TransitionManager.beginDelayedTransition(
                it,
                transition
            )
        }
    }


    override fun getViewModel(): BaseViewModel = charactersListViewModel

}