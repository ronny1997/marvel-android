package com.ronny.marvel.features.characters.characterslist

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.databinding.FragmentCharactersListBinding
import kotlinx.coroutines.flow.collect
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
        return binding.root
    }

    private fun initView() {
        binding.rvCharactersList.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter ?: run {
            adapter = CharacterAdapter()
        }
        binding.rvCharactersList.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    binding.character = it
                }
            }
        }
        initListeners()
    }

    private fun initListeners() {
        binding.swpLayout.setOnRefreshListener {
            adapter?.itemCount?.let {
                charactersListViewModel.lastVisibility.value = it
            }
        }
        adapter?.let {
            it.clickListener = { imgV, id ->
                if (!binding.swpLayout.isRefreshing) {
                    fragmentNavigatorExtras = FragmentNavigatorExtras(imgV to "image_big")
                    id?.let { itemId ->
                        charactersListViewModel.goToCharacterDetail(itemId)
                    }
                }
            }
        }
    }



    override fun getViewModel(): BaseViewModel = charactersListViewModel

}