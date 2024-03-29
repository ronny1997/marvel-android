package com.ronny.marvel.presentation.charactersdetail

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
import com.ronny.marvel.common.extensions.themeColor
import com.ronny.marvel.common.ui.BaseFragment
import com.ronny.marvel.common.ui.BaseViewModel
import com.ronny.marvel.databinding.FragmentDetailCharactersBinding
import com.ronny.marvel.presentation.model.CharacterView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private var _binding: FragmentDetailCharactersBinding? = null
    private val binding get() = _binding!!

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCharactersBinding.inflate(inflater, container, false)
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
        val characterView = arguments?.get("characterView") as CharacterView
        binding.viewModel = characterDetailViewModel
        ViewCompat.setTransitionName(binding.root, "character_card_${characterView.id}")
        characterDetailViewModel.getCharactersList(characterView.id ?: -1)
        lifecycleScope.launchWhenStarted {
            characterDetailViewModel.characterUiState.collect { characterItemUiState ->
                if (characterItemUiState.isLoading) {
                    binding.prbCharacterItem.visibility = View.VISIBLE
                }
                if (characterItemUiState.error.isNotEmpty()) {
                    binding.prbCharacterItem.visibility = View.GONE
                    alertDialogError(characterItemUiState.error) {
                        characterDetailViewModel.getCharactersList(characterView.id ?: -1)
                    }
                }
                characterItemUiState.charactersListView?.let {
                    binding.prbCharacterItem.visibility = View.GONE
                    binding.characterView = it
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel(): BaseViewModel = characterDetailViewModel
}