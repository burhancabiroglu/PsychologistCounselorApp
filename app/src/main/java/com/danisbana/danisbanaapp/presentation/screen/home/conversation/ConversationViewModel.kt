package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ConversationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ConversationState> = MutableStateFlow(ConversationState())
    val stateFlow: StateFlow<ConversationState> = _stateFlow.asStateFlow()


    fun setStateArgs(args:ConversationArgs?) {
        args?:return
        _stateFlow.value.loadingState.show()
        _stateFlow.value.channelName = args.message.title
        _stateFlow.value.message = args.message
        _stateFlow.value.loadingState.hide()
    }
}
