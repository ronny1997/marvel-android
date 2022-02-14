package com.ronny.marvel.presentation.characterslist

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
import com.ronny.marvel.common.ui.BaseFragment
import com.ronny.marvel.common.ui.BaseViewModel
import com.ronny.marvel.databinding.FragmentCharactersListBinding
import com.ronny.marvel.presentation.model.CharacterView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class CharactersListFragment : BaseFragment(), CharacterAdapter.CharacterAdapterListener {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    private var _characterAdapter: CharacterAdapter? = CharacterAdapter(this)
    private val characterAdapter get() = _characterAdapter

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
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        binding.viewModel = charactersListViewModel
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    alertDialogError(charactersUiState.error) {
                        val lastValue = charactersListViewModel.lastVisibility.replayCache.last()
                        runBlocking {
                            charactersListViewModel.getCharacterRemote(lastValue)
                        }
                    }
                }
                charactersUiState.charactersListView?.let {
                    binding.prbCharacter.visibility = View.GONE
                    binding.swpLayout.isRefreshing = false
                    binding.listCharacterView = it
                }
            }
        }
    }

    private fun initView() {
        binding.rvCharactersList.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun initListeners() {
        binding.swpLayout.setOnRefreshListener {
            characterAdapter?.itemCount?.let {
                charactersListViewModel.lastVisibility.value = it
            }
        }
    }

    override fun clickListener(view: View, characterView: CharacterView?) {
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
            characterView?.let {
                charactersListViewModel.goToCharacterDetail(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel(): BaseViewModel = charactersListViewModel

}