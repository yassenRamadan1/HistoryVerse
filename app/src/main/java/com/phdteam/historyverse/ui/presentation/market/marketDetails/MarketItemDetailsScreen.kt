package com.phdteam.historyverse.ui.presentation.market.marketDetails

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.modifier.noRippleEffect
import com.phdteam.historyverse.ui.presentation.details.components.ReviewTab
import com.phdteam.historyverse.ui.presentation.market.components.MarketProductItem
import com.phdteam.historyverse.ui.presentation.market.components.SimilarProductItem
import com.phdteam.historyverse.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun MarketItemDetailsScreen(
    itemId: Int?,
    viewModel: MarketItemDetailsViewModel = koinViewModel(parameters = { parametersOf(itemId) }),
    navigateTo: (MarketDetailsUiEffect) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    ItemDetailsContent(state = state, viewModel = viewModel, onNavigateBack)

    LaunchedEffect(key1 = !state.isLoading == !state.isError) {
        viewModel.effect.collect { effect ->
            onEffect(effect, context, navigateTo)
        }
    }
}

private fun onEffect(
    effect: MarketDetailsUiEffect?,
    context: Context,
    navigateTo: (MarketDetailsUiEffect) -> Unit
) {

    when (effect) {
        MarketDetailsUiEffect.OnMarketDetailsError -> Toast.makeText(
            context,
            "error",
            Toast.LENGTH_SHORT
        ).show()

        is MarketDetailsUiEffect.NavigateToReview -> navigateTo(effect)
        else -> {}
    }
}

@Composable
private fun ItemDetailsContent(
    state: MarketItemDetailsUiState,
    viewModel: MarketItemDetailsViewModel,
    navigateBack: () -> Unit
) {
    val rating = state.rating

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Theme.colors.background)

    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.4f)
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(state.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
                        contentScale = ContentScale.Fit
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.noRippleEffect {
                                    navigateBack()
                                }
                            )
                            Text(
                                text = "Artifacts",
                                color = Color.White,
                                style = Theme.typography.titleSmall
                            )
                        }
                        Icon(
                            imageVector =
                                Icons.TwoTone.Favorite,
                            contentDescription = null,
                            tint = if (state.isFavorite) Color.Red else Color.White,
                            modifier = Modifier.noRippleEffect { viewModel.onClickFavorite() }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(.72f)
                    .align(Alignment.BottomCenter)
                    .background(Color.White),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(text = state.name, style = Theme.typography.titleSmall)
                        Text(
                            text = state.price,
                            style = Theme.typography.bodyMedium,
                            color = Color(0xFF121212),
                            modifier = Modifier.alpha(.7f)
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = state.shopImage,
                                ), contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                            )
                            Text(text = state.shopName, style = Theme.typography.bodyMedium)
                        }
                        Row(
                            modifier = Modifier.noRippleEffect {
                                viewModel.onReview()
                            },
                            horizontalArrangement = Arrangement.spacedBy(4.dp),

                            ) {
                            repeat(rating.toInt()) {
                                Image(
                                    painter = painterResource(id = R.drawable.star_smooth),
                                    contentDescription = "",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            repeat(ceil(rating - rating.toInt()).toInt()) {
                                Image(
                                    painter = painterResource(id = R.drawable.star_half),
                                    contentDescription = "",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            repeat(floor(5 - rating).toInt()) {
                                Image(
                                    painter = painterResource(id = R.drawable.star_empty),
                                    contentDescription = "",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Text(
                                text = rating.toString(),
                                style = Theme.typography.labelMedium,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                    Button(
                        onClick = { viewModel.addToCart(state.itemId) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD29023)),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                        modifier = Modifier.align(Alignment.Top)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(text = "Add to cart", color = Color.White)
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Description", style = Theme.typography.titleSmall)
                    Text(
                        state.description,
                        modifier = Modifier,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = Theme.typography.bodyMedium,
                        color = Color(0xFF717171)
                    )

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Similar Products",
                            style = Theme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF313131),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Text(
                            text = "view more>",
                            style = Theme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF9D5705),
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.similarProducts.size) { index ->
                            SimilarProductItem(
                                item = state.similarProducts[index],
                                onItemClick = viewModel::onItemClick
                            )
                        }
                    }
                }
                ReviewTab(state = state.reviewTabState, onReview = viewModel::onReview)
            }


        }
    }
}

@Preview
@Composable
private fun Preview() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD29023)),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = null,
            tint = Color.White
        )
        Text(text = "Add to cart", color = Color.White)
    }
}
