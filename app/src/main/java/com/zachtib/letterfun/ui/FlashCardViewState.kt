package com.zachtib.letterfun.ui

sealed class FlashCardViewState {
    object Initializing : FlashCardViewState()
    data class Displaying(val displayed: String) : FlashCardViewState()
}