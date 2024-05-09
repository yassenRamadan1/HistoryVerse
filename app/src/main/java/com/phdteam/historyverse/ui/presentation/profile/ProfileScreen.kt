package com.phdteam.historyverse.ui.presentation.profile

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.ProfileOptionButton
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


    ProfileContent(
        state = state, onNavFavorite = onNavFavorite
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
    state: ProfileUIState,
    onNavFavorite: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gold),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )

        {
            Box(contentAlignment = Alignment.BottomEnd) {

                var imageUri by remember {
                    mutableStateOf<Uri?>(null)
                }
                val galleryLauncher = rememberLauncherForActivityResult(

                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri ->
                        uri?.let {
                            imageUri = it
                        }
                    }
                )

                imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "",
                        modifier = Modifier
                            .size(164.dp)
                            .padding(24.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Button(
                    onClick = { galleryLauncher.launch("image/*")},
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cam_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(48.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Osama bin Ladin",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "+911",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }


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







        if (state.isLoading) {
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
    ProfileContent(state = states, onNavFavorite = {})
}
