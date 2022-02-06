package com.ronny.marvel.features.characters.charactersdetail

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.databinding.FragmentDetailCharactersBinding
import com.ronny.marvel.features.characters.model.CharacterItemView
import javax.inject.Inject

class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailCharactersBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CharacterDetailViewModel>

    private val charactersListViewModel: CharacterDetailViewModel by lazy { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterItemView = arguments?.get("characterItemView") as CharacterItemView
        characterItemView.let {
            binding.characterItemView = it
        }
    }

    override fun getViewModel(): BaseViewModel = charactersListViewModel
}