package com.seeho.lolapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.seeho.domain.model.DomainChampion
import com.seeho.domain.model.SaveableChampionsResource
import com.seeho.lolapplication.R
import com.seeho.lolapplication.viewModel.CombineUiState
import com.seeho.lolapplication.viewModel.MainViewModel

@Composable
fun ChampionCard(
    item: DomainChampion,
    isBookmarked: Boolean,
    onToggleBookmark : () -> Unit
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 400.dp, height = 140.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(10.dp)
        ){
            AsyncImage(
                model = "https://ddragon.leagueoflegends.com/cdn/13.20.1/img/champion/${item.id}.png",
                placeholder = painterResource(id = R.drawable.ic_loading),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp,100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = onToggleBookmark,
                    Modifier.padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = if(isBookmarked) {Icons.Default.Favorite} else {Icons.Default.FavoriteBorder},
                        tint = if(isBookmarked) {Color.Red} else {Color.DarkGray},
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChampionsList(
    champions: List<SaveableChampionsResource>,
    onToggleBookmark : (String,Boolean) -> Unit
){
    LazyColumn{
        items(champions){
            ChampionCard(
                it.champion,
                isBookmarked = it.isBookmarked,
                onToggleBookmark = {
                    onToggleBookmark(it.champion.id,!it.isBookmarked)
                }
            )
        }
    }
}

@Composable
fun ChampionScreen(
    uiState: CombineUiState,
    onToggleBookmark: (String,Boolean) -> Unit
){
    Column (
        modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        when(uiState){
             CombineUiState.Loading -> {
                 CircularProgressIndicator(
                     Modifier.size(100.dp,100.dp),
                     color = Color.DarkGray,
                     strokeWidth = 5.dp
                 )
            }
            is CombineUiState.Champions -> {
                ChampionsList(champions = uiState.champions, onToggleBookmark = onToggleBookmark)
            }
        }
    }
}

class MainFragment : Fragment(){
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val uiState by mainViewModel.combineUiState.collectAsStateWithLifecycle()
                Log.e("TAG", "하이: $uiState.", )
                ChampionScreen(uiState = uiState, onToggleBookmark = mainViewModel::toggleBookmarkButton)
            }
        }
    }
}