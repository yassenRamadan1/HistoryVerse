@file:OptIn(ExperimentalMaterial3Api::class)

package com.phdteam.historyverse.ui.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwipeCardToDismiss(
    onDismiss: (Int) -> Unit,
    id: Int,
    modifier: Modifier,
    content: @Composable () -> Unit
) {

    val state = rememberDismissState(
        confirmValueChange = {
            when (it) {
                DismissValue.Default -> false
                DismissValue.DismissedToEnd -> false
                DismissValue.DismissedToStart -> {
                    onDismiss(id)
                    true
                }
            }
        },
    )
    SwipeToDismiss(
        modifier = modifier,
        state = state,
        background = {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Red,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        },
        dismissContent = {
            content()
        },
        directions = setOf(DismissDirection.EndToStart)
    )
}