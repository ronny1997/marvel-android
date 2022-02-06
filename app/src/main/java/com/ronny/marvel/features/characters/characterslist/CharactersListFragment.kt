package com.ronny.marvel.features.characters.characterslist

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var adapter: CharacterAdapter? = null

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
        initView()
        initListeners()
        animations(container)
        return binding.root
    }

    private fun initView() {
        binding.rvCharactersList.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = CharacterAdapter()
        binding.rvCharactersList.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            charactersListViewModel.charactersUiState.collect { charactersUiState ->
                if (charactersUiState.isLoading) {
                }
                if (charactersUiState.error.isNotEmpty()) {
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
        val layoutManager = binding.rvCharactersList.layoutManager as GridLayoutManager
        binding.rvCharactersList.addOnScrollListener((object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastPositionVisible = layoutManager.findLastVisibleItemPosition() + 1
                recyclerView.adapter?.itemCount?.let {
                    if (it == lastPositionVisible && binding.prbCharacters.visibility == View.GONE) {
                        binding.prbCharacters.visibility = View.VISIBLE
                        charactersListViewModel.lastVisibility.value = it
                    }
                }
            }
        }))
        adapter?.let {
            it.clickListener = { imgV, id ->
                fragmentNavigatorExtras = FragmentNavigatorExtras(imgV to "image_big")
                id?.let { itemId->
                    charactersListViewModel.goToCharacterDetail(itemId)
                }

            }
        }
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