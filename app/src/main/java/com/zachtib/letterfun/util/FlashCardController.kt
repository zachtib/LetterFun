package com.zachtib.letterfun.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlashCardController<T>(
    private val values: List<T>,
    var shuffle: Boolean = false
) {
    private val _displayed = MutableStateFlow(values.first())
    val displayed: StateFlow<T> = _displayed
    private var currentIndex = 0

    fun next() {
        currentIndex = if (shuffle) {
            values.indices
                .filterNot { it == currentIndex }
                .random()
        } else {
            (currentIndex + 1) % values.size
        }
        _displayed.value = values[currentIndex]
    }
}