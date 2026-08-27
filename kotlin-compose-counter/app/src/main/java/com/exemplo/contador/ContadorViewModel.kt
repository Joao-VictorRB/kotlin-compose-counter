package com.exemplo.contador

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ContadorViewModel(
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    var contador by mutableStateOf(savedStateHandle.get<Int>(CHAVE_CONTADOR) ?: 0)
        private set

    fun incrementar() {
        contador += 1
        savedStateHandle[CHAVE_CONTADOR] = contador
    }

    fun decrementar() {
        if (contador > 0) {
            contador -= 1
            savedStateHandle[CHAVE_CONTADOR] = contador
        }
    }

    fun zerar() {
        contador = 0
        savedStateHandle[CHAVE_CONTADOR] = contador
    }

    private companion object {
        const val CHAVE_CONTADOR = "chave_contador"
    }
}
