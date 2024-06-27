package com.phdteam.historyverse.ui.presentation.payment

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.components.HVBackTopAppBar
import com.phdteam.historyverse.ui.components.HVButton
import com.phdteam.historyverse.ui.components.HVPaymentCard
import com.phdteam.historyverse.ui.components.HVPaymentFailCard
import com.phdteam.historyverse.ui.components.HVTextField
import com.phdteam.historyverse.ui.theme.Theme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = koinViewModel(),
    onNavigateBack: () -> Unit

) {

    val state by viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    PaymentContent(
        states = state,
        onUserCardNameChanged = viewModel::onChangeCardName,
        onUserCardNumberChanged = viewModel::onChangeCardNumber,
        onUserValidDateChanged = viewModel::onChangeValidDate,
        onUserCvvChanged = viewModel::onChangeCvv,
        onNavigateBack = onNavigateBack,
        onClickedNext = viewModel::onClickNext,
        onClickGoBack = viewModel::onClickGoBack
    )

    LaunchedEffect(key1 = state.isSuccess) {
        viewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}


private fun onEffect(
    effect: PaymentUIEffect?,
    context: Context
) {
    when (effect) {
        PaymentUIEffect.PaymentError -> Toast.makeText(
            context,
            "Please correct your information",
            Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}


@Composable
private fun PaymentContent(
    states: PaymentUIState,
    onUserCardNameChanged: (String) -> Unit,
    onUserCardNumberChanged: (String) -> Unit,
    onUserValidDateChanged: (String) -> Unit,
    onUserCvvChanged: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onClickedNext: () -> Unit,
    onClickGoBack: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                HVPaymentCard()
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
                    text = states.cardName,
                    onValueChange = onUserCardNameChanged,
                    label = "Card Name"
                )
                Spacer(modifier = Modifier.height(16.dp))
                HVTextField(
                    text = states.cardNumber,
                    onValueChange = onUserCardNumberChanged,
                    label = "Card Number"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HVTextField(
                        text = states.validDate,
                        onValueChange = onUserValidDateChanged,
                        label = "Valid Date",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    HVTextField(
                        text = states.cvv,
                        onValueChange = onUserCvvChanged,
                        label = "Cvv",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                HVButton(
                    title = "Next",
                    onClick = onClickedNext,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        if (states.showWarning) {
            HVPaymentFailCard(
                onClickGoBack = onClickGoBack,
                modifier = Modifier
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PaymentProfileScreen() {

    val states = PaymentUIState()
    PaymentContent(states = states,
        onNavigateBack = {},
        onUserCardNameChanged = {},
        onUserCardNumberChanged = {},
        onUserValidDateChanged = {},
        onUserCvvChanged = {},
        onClickedNext = {},
        onClickGoBack = {})
}

