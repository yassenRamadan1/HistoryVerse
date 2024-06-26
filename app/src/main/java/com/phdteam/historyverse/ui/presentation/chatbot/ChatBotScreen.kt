package com.phdteam.historyverse.ui.presentation.chatbot

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.chatbot.composable.MessageCard
import com.phdteam.historyverse.ui.presentation.chatbot.composable.SendTextField
import com.phdteam.historyverse.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.IOException


@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ChatBotScreen(
    viewModel: ChatBotViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    LaunchedEffect(key1 = Lifecycle.State.RESUMED) {
        viewModel.setRoles(
            user = context.getString(R.string.userRole),
            model = context.getString(R.string.modelRole)
        )
    }
    val permissionState = remember { mutableStateOf(false) }


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//            permissionState.value = permissions[Manifest.permission.RECORD_AUDIO] == true &&
//                    permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
        }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                )
            )
        } else {
            permissionState.value = true
        }
    }

    val audioFile = File(context.getExternalFilesDir(null), "ChatBotAudioRecord .3gp")
    val outPutFilePath = File(context.getExternalFilesDir(null), "ChatBotAudioRecord .3gp")
    ChatBotContent(
        state = state,
        onValueChanged = viewModel::onChanceMessage,
        sendMessage = viewModel::onSendClicked,
        onCLickBack = onNavigateBack,
        onRecordClick = {
            viewModel.onRecordClick(
                context,
                outPutFilePath
            )
        },
        sendAudioToBot = viewModel::sendAudio,
        permissionGranted = permissionState.value
    )

}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ChatBotContent(
    state: ChatUiState,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    onCLickBack: () -> Unit,
    onRecordClick: () -> Unit,
    sendAudioToBot: (String) -> Unit,
    permissionGranted: Boolean
) {
    val context = LocalContext.current
    val mediaRecorder: MediaRecorder = MediaRecorder(context)
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = state.messages) {
        listState.animateScrollToItem(state.messages.lastIndexOrZero())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(1.dp),
                title = {
                    Text(
                        text = "Chat Bot",
                        style = Theme.typography.labelLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onCLickBack() }) {
                        Icon(
                            painterResource(R.drawable.arrow),
                            contentDescription = "Go back",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .noRippleEffect { onCLickBack() }
                                .rotate(180f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Theme.colors.background
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.hback),
                contentDescription = "background chat screen",
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Bottom,
                    contentPadding = PaddingValues(vertical = 24.dp),
                ) {
                    items(items = state.messages) {
                        MessageCard(it, Modifier.animateItemPlacement())
                    }
                }
                SendTextField(
                    text = state.message,
                    onValueChanged = onValueChanged,
                    sendMessage = sendMessage,
                    canMessage = state.canNotSendMessage,
                    isRecording = state.isRecording,
                    onRecordButtonClick = {
                        if (permissionGranted) {
                            onRecordClick()
                        } else
                            Toast.makeText(context, "audio permission required", Toast.LENGTH_SHORT)
                                .show()
                    }
                )
            }

            if (state.isLoading)
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Theme.colors.primary
                )
        }


    }

    LaunchedEffect(key1 = true) {
        listState.animateScrollToItem(index = state.messages.lastIndexOrZero())
    }

}


fun <T> List<T>.lastIndexOrZero(): Int {
    return if (this.isEmpty()) 0 else this.size - 1
}