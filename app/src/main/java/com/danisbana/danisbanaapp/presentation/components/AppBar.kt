package com.danisbana.danisbanaapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danisbana.danisbanaapp.presentation.theme.QueenBlue
import com.danisbana.danisbanaapp.presentation.theme.White

@Composable
fun MAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    logoutEnabled: Boolean = false,
    ) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp) ,
        backgroundColor = White,
        elevation = 3.dp,
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.primary
            )
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (logoutEnabled) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Logout ,
                            contentDescription = "Logout",
                            tint = MaterialTheme.colors.error.copy(0.5f)
                        )
                    }
                }
            }
        }
    }
}