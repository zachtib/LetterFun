package com.zachtib.letterfun.ui.emoji

import androidx.lifecycle.ViewModel
import com.zachtib.letterfun.ui.FlashCardViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EmojiViewModel : ViewModel() {

    private val _state = MutableStateFlow<FlashCardViewState>(
        FlashCardViewState.Displaying(emoji.random())
    )
    val state: StateFlow<FlashCardViewState> = _state

    companion object {
        private val emoji = listOf(
            "🐈", "🐕", "🐢", "🍪", "🦊",
            "🦁", "🐅", "🐺", "🍎", "🍏",
            "🍌", "🍕",
        )
    }


    fun onTap() {
        state.value.let { state ->
            val next = when (state) {
                FlashCardViewState.Initializing -> emoji
                is FlashCardViewState.Displaying -> emoji.filterNot { it == state.displayed }
            }.random()
            _state.value = FlashCardViewState.Displaying(next)
        }
    }
}