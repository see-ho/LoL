package com.seeho.lolapplication.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seeho.domain.model.DomainChampion
import com.seeho.lolapplication.databinding.ItemChampionBinding

class ChampionsAdapter(): RecyclerView.Adapter<ChampionsAdapter.ChampionsViewHolder>(){
    private var items: List<DomainChampion> = ArrayList()

    inner class ChampionsViewHolder(private val binding: ItemChampionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DomainChampion){
            binding.apply {
                tvName.text = item.name
                Glide.with(ivImg)
                    .load("https://ddragon.leagueoflegends.com/cdn/13.20.1/img/champion/${item.id}.png")
                    .into(ivImg)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ChampionsViewHolder {
        val binding = ItemChampionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChampionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainChampion>){
        items = newItems
        Log.e("TAG", "setData: 잘 받았어!${items}", )
        notifyDataSetChanged()
    }

}