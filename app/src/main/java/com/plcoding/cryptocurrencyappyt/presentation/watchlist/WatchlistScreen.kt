package com.plcoding.cryptocurrencyappyt.presentation.Watchlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.CoinListItem
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.HeadersLine

@Composable
fun WatchlistScreen(
    navController: NavController,
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val emptyList = viewModel.emptyList

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeadersLine()
            Divider(Modifier.padding(6.dp), color = Color.LightGray)
            if(!emptyList.value){
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.coins) { coin ->
                        CoinListItem(
                            coin = coin,
                            onItemClick = {
                                navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                            }
                        )
                        Divider(Modifier.padding(3.dp), color = Color.Green)
                    }
                }
            }else{
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "Add coins to your watchlist to display here"
                )
            }
            
            
        }

        if (state.error.isNotBlank()) {//if it contains an error
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )

        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}