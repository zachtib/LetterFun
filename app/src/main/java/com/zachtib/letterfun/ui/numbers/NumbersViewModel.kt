package com.zachtib.letterfun.ui.numbers

import androidx.lifecycle.ViewModel
import com.zachtib.letterfun.ui.FlashCardViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class NumbersViewModel : ViewModel() {
    private val _state = MutableStateFlow<FlashCardViewState>(FlashCardViewState.Initializing)
    val state: StateFlow<FlashCardViewState> = _state

    private var currentValue = 0
    private var showZero: Boolean = true
    private var upperBound: Int = 20
    private var isRandom: Boolean = false

    fun start(showZero: Boolean, upperBound: Int, isRandom: Boolean) {
        if (upperBound < 1) {
            Timber.w("Shouldn't be this low! Aborting")
            return
        }
        this.showZero = showZero
        this.upperBound = upperBound
        this.isRandom = isRandom
        currentValue = if (showZero) 0 else 1
        _state.value = FlashCardViewState.Displaying(currentValue.toString())
    }

    fun onScreenTapped() {
        if (isRandom) {
            displayRandomCharacter()
        } else {
            displayNextCharacter()
        }
    }

    private fun displayNextCharacter() {
        currentValue++
        if (currentValue > upperBound) {
            currentValue = if (showZero) 0 else 1
        }
        _state.value = FlashCardViewState.Displaying(currentValue.toString())
    }

    private fun displayRandomCharacter() {
        _state.value = FlashCardViewState.Displaying(
            (currentValue..upperBound).random().toString()
        )
    }
}