package com.test.gravardados.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.gravardados.R

//    RecyclerView
class FoodAdapter(private val foodList: List<FoodItem>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.categoryText.text = "Categoria: ${foodItem.category}"
        holder.nameText.text = "Nome: ${foodItem.name}"
    }

    override fun getItemCount(): Int = foodList.size

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryText: TextView = view.findViewById(R.id.categoryText)
        val nameText: TextView = view.findViewById(R.id.nameText)
    }
}
    data class FoodItem(
        val category: String,
        val name: String
    )
