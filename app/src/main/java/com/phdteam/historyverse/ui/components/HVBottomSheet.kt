package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phdteam.historyverse.ui.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HVBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    showDragHandle: Boolean = true,
    containerColor: Color = Theme.colors.background,
    state: SheetState = rememberModalBottomSheetState(
        confirmValueChange = {
            true
        }
    ),
    content: @Composable (ColumnScope.() -> Unit),
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = state,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            if (showDragHandle)
                BottomSheetDefaults.DragHandle(color = Theme.colors.primary)
            else
                BottomSheetDefaults.HiddenShape
        },
        containerColor = containerColor,
        content = content
    )
}
