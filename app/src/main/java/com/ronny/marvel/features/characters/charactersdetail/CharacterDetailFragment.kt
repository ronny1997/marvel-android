package com.ronny.marvel.features.characters.charactersdetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.google.android.material.transition.MaterialContainerTransform
import com.ronny.marvel.R
import com.ronny.marvel.core.common.ViewModelFactory
import com.ronny.marvel.core.extensions.themeColor
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
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterItemView = arguments?.get("characterItemView") as CharacterItemView
        ViewCompat.setTransitionName(binding.root,"character_card_${characterItemView.id}")
        characterItemView.let {
            binding.characterItemView = it
        }
    }
    override fun getViewModel(): BaseViewModel = charactersListViewModel
}