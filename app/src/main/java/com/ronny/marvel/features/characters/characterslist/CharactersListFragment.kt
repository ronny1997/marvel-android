package com.ronny.marvel.features.characters.characterslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.ronny.marvel.R
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.databinding.FragmentCharactersListBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class CharactersListFragment : BaseFragment() {

    private lateinit var binding: FragmentCharactersListBinding
    private var characterAdapter: CharacterAdapter = CharacterAdapter()
    private var etag: String = ""

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CharactersListViewModel>

    private val charactersListViewModel: CharactersListViewModel by lazy { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        initListeners()
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        lifecycleScope.launchWhenStarted {
            charactersListViewModel.charactersUiState.collect { charactersUiState ->
                if (charactersUiState.isLoading) {

                }
                if (charactersUiState.error.isNotEmpty()) {
                    //show error
                    binding.swpLayout.isRefreshing = false
                }
                charactersUiState.charactersListView?.let {
                    binding.swpLayout.isRefreshing = false
                    it.etag?.let { eTag ->
                        if (etag != eTag) {
                            etag = eTag
                            binding.character = it
                        }
                    }
                }
            }
        }
        initListeners()
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
        characterAdapter.let {
            it.clickListener = { view, id ->
                if (!binding.swpLayout.isRefreshing) {
                    exitTransition = MaterialElevationScale(false).apply {
                        duration =
                            resources.getInteger(R.integer.reply_motion_duration_large).toLong()
                    }
                    reenterTransition = MaterialElevationScale(true).apply {
                        duration =
                            resources.getInteger(R.integer.reply_motion_duration_large).toLong()
                    }
                    val characterDetailTransitionName =
                        getString(R.string.character_detail_transition_name)
                    fragmentNavigatorExtras =
                        FragmentNavigatorExtras(view to characterDetailTransitionName)
                    id?.let { itemId ->
                        charactersListViewModel.goToCharacterDetail(itemId)
                    }
                }
            }
        }
    }
    override fun getViewModel(): BaseViewModel = charactersListViewModel
}