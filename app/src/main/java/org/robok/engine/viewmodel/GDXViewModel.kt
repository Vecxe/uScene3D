package org.robok.engine.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GDXViewModel : ViewModel() {

  private var _isOptionsOpen by mutableStateOf(false)
  val isOptionsOpen: Boolean
    get() = _isOptionsOpen

  fun setOptionsOpen(state: Boolean) {
    _isOptionsOpen = state
  }
}
