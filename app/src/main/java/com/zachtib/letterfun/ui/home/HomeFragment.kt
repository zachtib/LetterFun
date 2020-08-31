package com.zachtib.letterfun.ui.home

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zachtib.letterfun.R
import com.zachtib.letterfun.databinding.HomeFragmentBinding
import com.zachtib.letterfun.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {
    private val binding by viewBinding(HomeFragmentBinding::bind)

    override fun onStart() {
        super.onStart()

        binding.abcButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLettersFragment(
                isUpperCase = true,
                isRandom = false
            )
            findNavController().navigate(action)
        }
        binding.lowercaseButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLettersFragment(
                isUpperCase = false,
                isRandom = false
            )
            findNavController().navigate(action)
        }
        binding.numbersButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_homeFragment_to_numbersFragment)
        }
    }
}