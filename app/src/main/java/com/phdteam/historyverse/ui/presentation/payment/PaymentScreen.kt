package com.phdteam.historyverse.ui.presentation.payment

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.HVButton
import com.phdteam.historyverse.ui.components.HVTextField
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = koinViewModel(),
    onNavPayment: () -> Unit,
    onNavigateBack: () -> Unit

) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    PaymentContent(
        states = state,
        onNavPayment = onNavPayment,
        onUserCardNameChanged = viewModel::onChangeCardName,
        onUserCardNumberChanged = viewModel::onChangeCardNumber,
        onUserValidDateChanged = viewModel::onChangeValidDate,
        onUserCvvChanged = viewModel::onChangeCvv,
        onNavigateBack = onNavigateBack
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(effect: PaymentUIEffect?, context: Context) {

    when (effect) {
        PaymentUIEffect.PaymentError -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        else -> {}
    }
}


@Composable
private fun PaymentContent(
    states: PaymentUIState,
    onNavPayment: () -> Unit,
    onUserCardNameChanged: (String) -> Unit,
    onUserCardNumberChanged: (String) -> Unit,
    onUserValidDateChanged: (String) -> Unit,
    onUserCvvChanged: (String) -> Unit,
    onNavigateBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        ) {
        HVBackTopAppBar(onNavigateBack)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.BottomStart
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.visa),
                    contentDescription = null
                )
                Card(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(Color.Transparent),
                ) {
                    Text(
                        text = "2244 4223 5342 2224",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesLight
                    )
                    Text(
                        text = "05/25",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesLight
                    )
                    Text(
                        text = "Abdelrahman Abdelwahab",
                        style = Theme.typography.labelLarge,
                        color = Theme.colors.primaryShadesLight
                    )

                }
            }
            Text(
                text = "Enter Your Smart Card Information",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark
            )
            Text(
                text = stringResource(R.string.card_details_warning_msg),
                style = Theme.typography.labelLarge,
                color = Theme.colors.secondaryShadesDark
            )
            Spacer(modifier = Modifier.height(16.dp))
            HVTextField(
                text = "",
                onValueChange = onUserCardNameChanged,
                label = "Card Name"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HVTextField(
                text = "",
                onValueChange = onUserCardNumberChanged,
                label = "Card Number"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HVTextField(
                    text = "",
                    onValueChange = onUserValidDateChanged,
                    label = "Valid Date",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                HVTextField(
                    text = "",
                    onValueChange = onUserCvvChanged,
                    label = "Cvv",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            HVButton(
                title = "Next",
                onClick = onNavPayment,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PaymentProfileScreen() {

    val states = PaymentUIState()
    PaymentContent(states = states,
        onNavPayment = {},
        onNavigateBack = {},
        onUserCardNameChanged = {},
        onUserCardNumberChanged = {},
        onUserValidDateChanged = {},
        onUserCvvChanged = {})
}

