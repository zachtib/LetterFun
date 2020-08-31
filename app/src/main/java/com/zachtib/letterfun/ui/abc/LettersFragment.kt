package com.zachtib.letterfun.ui.abc

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.zachtib.letterfun.R
import com.zachtib.letterfun.databinding.LettersFragmentBinding
import com.zachtib.letterfun.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LettersFragment : Fragment(R.layout.letters_fragment) {
    private val binding by viewBinding(LettersFragmentBinding::bind)
    private val viewModel by viewModels<LettersViewModel>()
    private val args by navArgs<LettersFragmentArgs>()


    override fun onStart() {
        super.onStart()

        viewModel.start(args.isUpperCase, args.isRandom)

        binding.root.setOnClickListener {
            viewModel.onScreenTapped()
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state is LetterViewState.Displaying) {
                    binding.letter.text = state.displayed
                }
            }
        }
    }
}