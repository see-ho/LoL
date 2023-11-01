package com.seeho.lolapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import com.seeho.lolapplication.R
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.seeho.domain.model.DomainChampion
import com.seeho.domain.model.Skin
import com.seeho.lolapplication.base.BaseFragment
import com.seeho.lolapplication.databinding.FragmentChampionDetailBinding
import com.seeho.lolapplication.viewModel.ChampionDetailViewModel
import com.seeho.lolapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionDetailFragment : Fragment(){
    private val mainViewModel: MainViewModel by activityViewModels()
    private val detailViewModel: ChampionDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent{ Detail(mainViewModel, detailViewModel)}
            Log.e("TAG", "onCreateView: ${mainViewModel.champion.value}", )
//            setContent{ Counting(modifier = Modifier.fillMaxSize())}
        }
    }
}

@Composable
fun Detail(mainViewModel: MainViewModel, detailViewModel: ChampionDetailViewModel ){
    val stringList by detailViewModel.stringList.collectAsStateWithLifecycle(emptyList())
    val champ = mainViewModel.champion.value!!

    Box(){
        backgroundImg(id = champ.id)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.padding(top=50.dp))
                iconImg(id = champ.id)
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    mainContent(champ = champ)
                    likeButton()
                    infoComment(cnt = stringList.size.toString())
                }
            }

            items(stringList) { item ->
                listContent(item)
            }
        }
    }
}

@Composable
fun backgroundImg(id: String){
    AsyncImage(
        model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${id}_0.jpg",
        placeholder = painterResource(id = R.drawable.ic_loading),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(16f / 9f)
        //.fillMaxWidth()
        //.size(250.dp)
    )
}
@Composable
fun iconImg(id: String){
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    val borderWidth = 4.dp
    AsyncImage(
        model = "https://ddragon.leagueoflegends.com/cdn/13.20.1/img/champion/${id}.png",
        placeholder = painterResource(id = R.drawable.ic_loading),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, rainbowColorsBrush),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

@Composable
fun mainContent(champ: DomainChampion){
    val name = champ.name
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    Color.Red,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
            ) {
                append(name[0])
            }

            withStyle(
                style = SpanStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            ) {
                append(name.substring(1 until name.length))
            }
        }
    )
    Text(
        text = champ.title,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic
    )
    Divider(
        thickness = 1.dp,
        color = Color.DarkGray,
        modifier = Modifier.padding(top = 10.dp)
    )
    Text(
        text = champ.blurb,
        fontSize = 14.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(top = 10.dp)
    )
    Divider(
        thickness = 1.dp,
        color = Color.DarkGray,
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun likeButton(){
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        var cnt by remember{ mutableStateOf(0) }
        Button(
            onClick = { cnt++ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.wrapContentSize()
        ) {
            Icon(imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
            Text(text ="좋아요", fontSize = 12.sp )
        }
        Text(
            text = cnt.toString(),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun infoComment(cnt: String){
    Row {
        Text(
            text = "댓글",
            fontSize = 14.sp,
            color = Color.DarkGray,
        )
        Text(
            text = cnt,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
    Divider(
        thickness = 1.dp,
        color = Color.DarkGray,
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun listContent(item: String){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(start = 10.dp, end = 10.dp)) {
        Text(text = item, modifier = Modifier.padding(top = 6.dp))
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun DetailTest(champ: DomainChampion){
    val stringList = arrayListOf<String>("1빠","2빠","3빠")
    Box(){
        backgroundImg(id = champ.id)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.padding(top=50.dp))
                iconImg(id = champ.id)
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    mainContent(champ = champ)
                    likeButton()
                    infoComment(cnt = stringList.size.toString())
                }
            }

            items(stringList) { item ->
                listContent(item)
            }
        }
    }
}

@Composable
fun Counting(modifier: Modifier){
    var cnt by remember{ mutableStateOf(0) }
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = cnt.toString(),
            fontSize = 30.sp,
        )
        Button(
            onClick = { cnt++ },
        ) {
            Text(text ="click Like Click like" )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DetailTest(DomainChampion(id="Aatrox", key="266", name="아트록스", title="다르킨의 검", blurb="한때는 공허에 맞서 싸웠던 슈리마의 명예로운 수호자 아트록스와 그의 종족은 결국 공허보다 위험한 존재가 되어 룬테라의 존속을 위협했지만, 교활한 필멸자의 마법에 속아넘어가 패배하게 되었다. 수백 년에 걸친 봉인 끝에, 아트록스는 자신의 정기가 깃든 마법 무기를 휘두르는 어리석은 자들을 타락시키고 육신을 바꾸는 것으로 다시 한번 자유의 길을 찾아내었다. 이제 이전의 잔혹한 모습을 닮은 육체를 차지한 아트록스는 세상의 종말과 오랫동안 기다려온 복수를...", /*skins = listOf(Skin(0,"스킨"))*/)
    )
    //Counting(modifier = Modifier.fillMaxSize())
    //Text(text = "hi")
}