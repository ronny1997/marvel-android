package com.ronny.marvel.features.characters.charactersdetail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.transition.MaterialContainerTransform
import com.ronny.marvel.R
import com.ronny.marvel.core.extensions.themeColor
import com.ronny.marvel.core.platform.BaseFragment
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.databinding.FragmentDetailCharactersBinding
import com.ronny.marvel.features.characters.model.CharacterItemView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailCharactersBinding

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()


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
        characterDetailViewModel.getCharactersList(characterItemView.id?:-1)
        lifecycleScope.launchWhenStarted {
            characterDetailViewModel.characterUiState.collect { characterItemUiState ->
                if (characterItemUiState.isLoading) {
                        binding.prbCharacterItem.visibility = View.VISIBLE
                }
                if (characterItemUiState.error.isNotEmpty()) {
                    binding.prbCharacterItem.visibility = View.GONE
                    alertDialogError(characterItemUiState.error)
                }
                characterItemUiState.charactersListView?.let {
                    binding.prbCharacterItem.visibility = View.GONE
                    binding.characterItemView = it
                }
            }
        }
    }
    override fun getViewModel(): BaseViewModel = characterDetailViewModel
}