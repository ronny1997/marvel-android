package com.ronny.marvel.features.characters.characterslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.ronny.marvel.R
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.databinding.FragmentCharactersListBinding
import com.ronny.marvel.features.characters.model.CharacterItemView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListFragment : BaseFragment(), CharacterAdapter.CharacterAdapterListener {

    private lateinit var binding: FragmentCharactersListBinding
    private var characterAdapter: CharacterAdapter = CharacterAdapter(this)

    private val charactersListViewModel: CharactersListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        binding.viewModel = charactersListViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        lifecycleScope.launchWhenStarted {
            charactersListViewModel.charactersUiState.collect { charactersUiState ->
                if (charactersUiState.isLoading) {
                    if (!binding.swpLayout.isRefreshing) {
                        binding.prbCharacter.visibility = View.VISIBLE
                    }
                }
                if (charactersUiState.error.isNotEmpty()) {
                    binding.prbCharacter.visibility = View.GONE
                    binding.swpLayout.isRefreshing = false
                    alertDialogError(charactersUiState.error)
                }
                charactersUiState.charactersListView?.let {
                    binding.prbCharacter.visibility = View.GONE
                    binding.swpLayout.isRefreshing = false
                    binding.character = it
                }
            }
        }
    }

    private fun initView() {
        binding.rvCharactersList.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = characterAdapter
        }
    }

    private fun initListeners() {
        binding.swpLayout.setOnRefreshListener {
            characterAdapter.itemCount.let {
                charactersListViewModel.lastVisibility.value = it
            }
        }
    }

    override fun clickListener(view: View, characterItemView: CharacterItemView?) {
        if (!binding.swpLayout.isRefreshing) {
            exitTransition = MaterialElevationScale(false).apply {
                duration =
                    resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration =
                    resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            fragmentNavigatorExtras =
                FragmentNavigatorExtras(view to view.transitionName)
            characterItemView?.let {
                charactersListViewModel.goToCharacterDetail(it)
            }
        }
    }

    override fun getViewModel(): BaseViewModel = charactersListViewModel

}