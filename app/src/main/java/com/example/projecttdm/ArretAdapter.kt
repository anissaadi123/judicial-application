package com.example.projecttdm

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import java.text.DateFormat
import java.text.SimpleDateFormat

class ArretAdapter(val arrets : ArrayList<Arret>) :RecyclerView.Adapter<ArretAdapter.ArretViewHolder>() {

    class ArretViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val num: TextView = itemView.findViewById(R.id.v_num_arret)
        val source: TextView = itemView.findViewById(R.id.v_source_arret)
        val date: TextView = itemView.findViewById(R.id.v_date_arret)
        val isFavorite: ImageView = itemView.findViewById(R.id.v_isfavorite_arret)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArretViewHolder {
        return ArretViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_arret,parent,false))
    }

    override fun onBindViewHolder(holder: ArretViewHolder, position: Int) {

        val format = SimpleDateFormat("dd-mm-yyyy")


        var item = arrets[position]
        holder.num.text = item.num.toString()
        holder.source.text = item.source

        holder.date.text = format.format(item.date)




        if(item.isFavorite == false){
            holder.isFavorite.setImageResource(R.drawable.ic_baseline_favorite_24_vide)
        }
        else{
            holder.isFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        holder.isFavorite.setOnClickListener{
            if(item.isFavorite == true){
                item.isFavorite = false
                holder.isFavorite.setImageResource(R.drawable.ic_baseline_favorite_24_vide)
            }
            else{
                item.isFavorite = true
                holder.isFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }

    }

    override fun getItemCount(): Int {
        return arrets.size
    }
}