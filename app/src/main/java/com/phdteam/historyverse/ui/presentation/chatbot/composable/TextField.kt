package com.phdteam.historyverse.ui.presentation.chatbot.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdteam.historyverse.ui.theme.Theme
import com.phdTeam.HistoryVerse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendTextField(
    modifier: Modifier = Modifier,
    text: String,
    canMessage: Boolean = false,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    isRecording: Boolean ,
    onRecordButtonClick: () -> Unit,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Theme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        maxLines = 4,
        readOnly = canMessage,
        value = text,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = "What is  your question?",
                style = Theme.typography.bodyMedium
            )
        },
        trailingIcon = {
            if (text.isNotEmpty())
                ButtonSend(onClickAction = sendMessage, isEnabled = text.isNotEmpty())
            else
                ButtonRecord(isRecording = isRecording, onClickAction = onRecordButtonClick)
        }
    )
}

@Composable
fun ButtonRecord(
    isRecording: Boolean,
    onClickAction: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(40.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(
            Theme.colors.primaryShadesLight,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(100.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = onClickAction,
    ) {
        if (isRecording)
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Clear,
                contentDescription = "stop recording button",
                tint = Color.Gray,
            )
        else
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.mic_icon),
                contentDescription = "record button",
                tint = Theme.colors.primary,
            )
    }
}

@Composable
fun ButtonSend(
    onClickAction: () -> Unit,
    isEnabled: Boolean = true,
) {
    Button(
        modifier = Modifier
            .width(40.dp)
            .padding(2.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            Theme.colors.primaryShadesLight,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(100.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = onClickAction,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.paper_airplane),
            contentDescription = "back button",
            tint = if (isEnabled) Theme.colors.primary else Color.Gray,
        )
    }
}

@Preview(showBackground = true, locale = "en")
@Composable
fun DefaultTextFieldPreview() {
    SendTextField(
        text = "",
        onValueChanged = {},
        sendMessage = {},
        onRecordButtonClick = {},
        isRecording = false
    )
}