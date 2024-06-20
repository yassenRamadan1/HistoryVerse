package com.phdteam.historyverse.ui.presentation.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.HVButton
import com.phdteam.historyverse.ui.components.HVSnackbar
import com.phdteam.historyverse.ui.components.HVTextField
import com.phdteam.historyverse.ui.presentation.auth.composables.TextWithClick
import com.phdteam.historyverse.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    navigateTo: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state) {
        if (state.isSignInSuccessful) {
            Toast.makeText(
                context, "Sign in successful", Toast.LENGTH_LONG
            ).show()
            navigateTo()
        }
        if (state.errorMessage != null && state.isError) {
            val result = snackbarHostState.showSnackbar(
                message = state.errorMessage!!,
                actionLabel = "Hide",
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.Dismissed || result == SnackbarResult.ActionPerformed) {
                viewModel.clearErrorState()
            }
        }
    }
    SignInScreenContent(
        state = state,
        onNavigateBack = onNavigateBack,
        onChangeEmail = viewModel::onChangeEmail,
        onChangeUserName = viewModel::onChangeUserName,
        onChangePassword = viewModel::onChangePassword,
        onClickSignIn = viewModel::onClickSignUp,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun SignInScreenContent(
    state: SignInUiState,
    onNavigateBack: () -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeUserName: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onClickSignIn: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            HVBackTopAppBar(onNavigateBack)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Text(
                    text = "Sign UP",
                    style = Theme.typography.titleLarge,
                    color = Theme.colors.primaryShadesDark,
                    fontSize = 30.sp
                )
                HVTextField(label = "Email", text = state.email, onValueChange = onChangeEmail)
                HVTextField(
                    label = "User Name",
                    text = state.userName,
                    onValueChange = onChangeUserName
                )
                HVTextField(
                    label = "Your PassWord",
                    text = state.password, onValueChange = onChangePassword,
                    keyboardType = KeyboardType.Password
                )
                HVButton(
                    title = "Sign Up",
                    onClick = {
                        focusManager.clearFocus()
                        onClickSignIn()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                SignWithOtherWays()
            }
        }

    }
    HVSnackbar(snackbarHostState = snackbarHostState)
}

@Composable
fun SignWithOtherWays() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "- Or -",
            style = Theme.typography.titleSmall,
            color = Theme.colors.ternaryShadesDark
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = ""
            )
        }
        TextWithClick(
            fullText = "Don't have an account? signUp",
            linkText = "signUp",
            onClick = {}
        )
    }
}
