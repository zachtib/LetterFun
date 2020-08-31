package com.zachtib.letterfun.ui.abc

import androidx.lifecycle.ViewModel
import com.zachtib.letterfun.Letters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class LettersViewModel : ViewModel() {
    private val _state = MutableStateFlow<LetterViewState>(LetterViewState.Initializing)

    val state: StateFlow<LetterViewState> = _state

    private var letterSet: List<String> = listOf()
    private var isRandom: Boolean = false
    private var currentIndex = -1

    fun start(isCapital: Boolean, isRandom: Boolean) {
        letterSet = if (isCapital) {
            Letters.CAPITALS
        } else {
            Letters.LOWERCASE
        }

        this.isRandom = isRandom
        if (isRandom) {
            displayRandomCharacter()
        } else {
            currentIndex = 0
            this._state.value = LetterViewState.Displaying(letterSet.first())
        }
    }

    fun onScreenTapped() {
        if (isRandom) {
            displayRandomCharacter()
        } else {
            displayNextCharacter()
        }
    }

    private fun displayNextCharacter() {
        if (letterSet.isNotEmpty()) {
            currentIndex++
            if (currentIndex >= letterSet.size) {
                currentIndex %= letterSet.size
            }
            this._state.value = LetterViewState.Displaying(letterSet[currentIndex])
        } else {
            Timber.w("letterSet was empty!")
        }
    }

    private fun displayRandomCharacter() {
        if (letterSet.isNotEmpty()) {
            this._state.value = LetterViewState.Displaying(letterSet.random())
        } else {
            Timber.w("letterSet was empty!")
        }
    }
}