package org.robok.engine.compose.model

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GdxViewModel : ViewModel() {
    
    private var _isOptionsOpen by mutableStateOf(false)
    val isOptionsOpen : Boolean
    get() = _isOptionsOpen
    
    fun setOptionsOpen(state: Boolean){
        _isOptionsOpen = state
    }
}
