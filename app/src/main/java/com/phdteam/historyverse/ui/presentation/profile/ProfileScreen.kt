package com.phdteam.historyverse.ui.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.ProfileOptionButton
import com.phdteam.historyverse.ui.components.ProfilePicData
import com.phdteam.historyverse.ui.theme.Gold
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onNavFavorite: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(

        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                viewModel.onUpdateImageUri(it)
            }
        }
    )
    ProfileContent(
        states = state,
        onNavFavorite = onNavFavorite,
        onClickProfilePic = {galleryLauncher.launch("image/*")}

    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: ProfileUIEffect?, context: Context) {

    when (effect) {
        ProfileUIEffect.ProfileError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun ProfileContent(
    states: ProfileUIState,
    onNavFavorite: () -> Unit,
    onClickProfilePic: () ->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gold),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {

        ProfilePicData(state = states) {   onClickProfilePic()  }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        )
        {
            Spacer(modifier = Modifier.height(24.dp))
            ProfileOptionButton(
                text = "Favorite",
                onClickOption = onNavFavorite,
                painter = R.drawable.back_arrow
            )
            ProfileOptionButton(
                text = "Cart",
                onClickOption = { },
                painter = R.drawable.back_arrow
            )
            ProfileOptionButton(
                text = "My ticket",
                onClickOption = { },
                painter = R.drawable.back_arrow
            )
            ProfileOptionButton(
                text = "Logout",
                onClickOption = { },
                painter = R.drawable.baseline_logout_24
            )
        }







        if (states.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "Profile screen",
                style = Theme.typography.bodyLarge,
                color = Theme.colors.primary
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewProfileScreen() {

    val states = ProfileUIState()
    ProfileContent(states = states,
        onNavFavorite = {},
        onClickProfilePic = {})
}
