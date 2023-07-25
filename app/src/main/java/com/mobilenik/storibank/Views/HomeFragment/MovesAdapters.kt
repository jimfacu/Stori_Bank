package com.mobilenik.storibank.Views.HomeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobilenik.storibank.Data.Model.Moves
import com.mobilenik.storibank.R
import com.mobilenik.storibank.databinding.ItemMoveBinding

class MovesAdapters (private var moves:List<Moves>):RecyclerView.Adapter<MovesAdapters.MovesViewHolder>() {

    private lateinit var context: Context
    lateinit var onItemClickListener:(Moves) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesViewHolder {
        val binding = ItemMoveBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return  MovesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        with(holder.binding){
            clMove.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            tvAmountMove.text = "$"+moves.get(position).Monto
            tvNameUser.text = moves.get(position).Nombre
            tvStateOrder.text = moves.get(position).Estado

        }

        holder.binding.root.setOnClickListener{
            onItemClickListener(moves.get(position))
        }
    }

    override fun getItemCount() = moves.size




    inner class MovesViewHolder(val binding: ItemMoveBinding): RecyclerView.ViewHolder(binding.root) {


    }


}