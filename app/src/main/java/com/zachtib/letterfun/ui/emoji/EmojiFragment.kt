package com.zachtib.letterfun.ui.emoji

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zachtib.letterfun.R
import com.zachtib.letterfun.databinding.FlashcardFragmentBinding
import com.zachtib.letterfun.ui.FlashCardViewState
import com.zachtib.letterfun.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmojiFragment : Fragment(R.layout.flashcard_fragment) {
    private val viewModel by viewModels<EmojiViewModel>()
    private val binding by viewBinding(FlashcardFragmentBinding::bind)

    override fun onStart() {
        super.onStart()

        binding.root.setOnClickListener {
            viewModel.onTap()
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state is FlashCardViewState.Displaying) {
                    binding.flashCardText.text = state.displayed
                }
            }
        }
    }
}