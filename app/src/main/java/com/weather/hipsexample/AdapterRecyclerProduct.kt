package com.weather.hipsexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weather.hipsexample.model.ModelProduct

class AdapterRecyclerProduct(
    private val list: List<ModelProduct>
) : RecyclerView.Adapter<AdapterRecyclerProduct.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView? = null
        var textViewDescription: TextView? = null

        init {
            textViewName = itemView.findViewById(R.id.itemTextViewProductName)
            textViewDescription = itemView.findViewById(R.id.itemTextViewProductDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_product, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName?.text = list[holder.adapterPosition].name
        holder.textViewDescription?.text = list[holder.adapterPosition].description
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
