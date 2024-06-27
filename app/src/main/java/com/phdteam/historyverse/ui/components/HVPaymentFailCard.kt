package com.phdteam.historyverse.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.theme.Theme

@Composable
fun HVPaymentFailCard(
    onClickGoBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))

    ) {
        Card(
            modifier = Modifier.align(Alignment.Center),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(24.dp),
            ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .padding(bottom = 8.dp),
                painter = painterResource(id = R.drawable.face_worried_svgrepo_com),
                contentDescription = ""
            )
            Text(
                text = "Payment Error",
                style = Theme.typography.labelLarge,
                color = Theme.colors.primaryShadesDark,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Your payment was unsuccessful. Please  try again",
                style = Theme.typography.labelLarge,
                color = Theme.colors.secondaryShadesDark,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            HVButton(
                title = "Go Back",
                onClick = { onClickGoBack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 16.dp)
                    .padding(top = 8.dp)
            )

        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HVPaymentFailCardPreview() {
    HVPaymentFailCard(
        onClickGoBack = {}
    )
}