package com.ronny.marvel.features.characters.characterslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.ronny.marvel.databinding.FragmentCharactersListBinding
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListFragment : BaseFragment() {
    private var mMap: GoogleMap? = null

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            charactersListViewModel.charactersUiState.collect { charactersUiState ->
                if (charactersUiState.isLoading) {
                }
                if (charactersUiState.error.isNotBlank()) {
                }
                charactersUiState.charactersListDto?.let {
                    binding.character = it
                }
            }
        }
        initListeners()
    }

    private fun initListeners() {
        binding.gvCharactersList.setOnScrollListener(object : AbsListView.OnScrollListener{
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                TODO("Not yet implemented")
            }

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (visibleItemCount==totalItemCount){

                }
            }

        })
    }

    override fun getViewModel(): BaseViewModel = charactersListViewModel

}