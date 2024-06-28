package com.phdteam.historyverse.ui.presentation.cart

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phdteam.historyverse.ui.components.HVAppBar
import com.phdteam.historyverse.ui.presentation.cart.components.CartItemCard
import com.phdteam.historyverse.ui.presentation.cart.components.SwipeCardToDismiss
import com.phdteam.historyverse.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel
import kotlin.math.ceil

@Composable
fun CartScreen(
    viewModel: CartViewModel = koinViewModel(),
    onNavigate: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    CartContent(
        state = state,
        onNavigateBack = onNavigateBack,
        onNextClick = onNavigate,
        onDeleteItem = viewModel::onDeleteItem
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartContent(
    state: CartUiState,
    onNavigateBack: () -> Unit,
    onNextClick: () -> Unit,
    onDeleteItem: (Int) -> Unit
) {

    Scaffold(topBar = {
        HVAppBar(
            title = "",
            onBack = onNavigateBack
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.animateContentSize()
            ) {
                items(state.items.size) { index ->
                    val item = state.items[index]
                    SwipeCardToDismiss(
                        onDismiss = onDeleteItem,
                        id = item.id, modifier = Modifier.animateItemPlacement()
                    ) {
                        CartItemCard(item)
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val color = Color(0xFFCCCFC7)
                Text(text = "Order info", style = Theme.typography.titleSmall)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Quantity", color = color)
                    Text(text = state.quantity.toString(), color = color)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total price", color = color)
                    val price =
                        if (ceil(state.totalPrice - state.totalPrice.toInt()) != 0.0) state.totalPrice else state.totalPrice.toInt()
                    Text(text = "$price EGP", color = color)
                }
            }

            Button(
                onClick = { onNextClick() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    style = Theme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}
