package com.zachtib.letterfun.ui.abc

sealed class LetterViewState {
    object Initializing : LetterViewState()
    data class Displaying(val displayed: String) : LetterViewState()
}