package com.danisbana.danisbanaapp.presentation.screen.home.messages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.danisbana.danisbanaapp.presentation.screen.home.root.HomeActions

@Composable
fun MessagesRoute(
    viewModel: MessagesViewModel = hiltViewModel(),
    homeActions: HomeActions = HomeActions()
) {
    val uiState by viewModel.stateFlow.collectAsState(MessagesState())
    val actions = rememberMessagesActions(viewModel)
    actions.routeConsultant = homeActions.routeConsultant
    MessagesScreen(uiState, actions)
}


@Composable
fun rememberMessagesActions(viewModel: MessagesViewModel): MessagesActions {
    return remember(viewModel) {
        MessagesActions()
    }
}