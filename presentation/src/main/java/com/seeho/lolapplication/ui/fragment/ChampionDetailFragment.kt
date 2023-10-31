//package com.seeho.lolapplication.ui.fragment
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.ComposeView
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.sp
//import com.seeho.lolapplication.base.BaseFragment
//import com.seeho.lolapplication.databinding.FragmentChampionDetailBinding
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ChampionDetailFragment : Fragment(){
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return ComposeView(requireContext()).apply {
//
//        }
//    }
//}
//
//@Composable
//fun Counting(modifier: Modifier){
//    var cnt by remember{ mutableStateOf(0) }
//    Column(modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = cnt.toString(),
//            fontSize = 30.sp,
//        )
//        Button(
//            onClick = { cnt++ },
//        ) {
//            Text(text ="click Like Click like" )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Counting(modifier = Modifier.fillMaxSize())
//}