//package com.seeho.lolapplication.ui.adapter
//
//import android.view.View
//import android.widget.ProgressBar
//import androidx.databinding.BindingAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.seeho.lolapplication.uiState.ChampionsUiState
//
//@BindingAdapter("show")
//fun ProgressBar.bindShow(uiState: ChampionsUiState) {
//    visibility = if (uiState is ChampionsUiState.Loading) View.VISIBLE else View.GONE
//}
//
//@BindingAdapter("championItems")
//fun RecyclerView.bindChampionItems(uiState: ChampionsUiState<Champions>) {
//    val boundAdapter = this.adapter
//    if (boundAdapter is ChampionAdapter) {
//        boundAdapter.submitList(uiState.successOrNull())
//    }
//}
//
