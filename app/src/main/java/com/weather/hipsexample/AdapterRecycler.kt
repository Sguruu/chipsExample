package com.weather.hipsexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.weather.hipsexample.model.ModelChips

class AdapterRecycler(
    private val list: List<ModelChips>,
    private val callbackElementChecked: (Boolean, TextView, CardView) -> Unit
) : RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {

    private var elementChecked: Int? = null
    private var elementCheckedOld: Int? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView? = null
        var textView: TextView? = null

        init {
            cardView = itemView.findViewById(R.id.itemCardView)
            textView = itemView.findViewById(R.id.itemTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView?.text = list[holder.adapterPosition].textChips

        holder.cardView?.setOnClickListener {
            elementChecked?.let {
                elementCheckedOld = it
            }
            elementChecked = holder.adapterPosition

            if (elementCheckedOld == null) {
                elementCheckedOld = elementChecked
            }

            // перересовка конкретного элемента
            notifyItemChanged(elementChecked!!)
            notifyItemChanged(elementCheckedOld!!)
        }

        elementChecked?.let {
            if (holder.textView != null &&
                holder.cardView != null
            ) {
                val cellActive = it == holder.adapterPosition
                callbackElementChecked.invoke(cellActive, holder.textView!!, holder.cardView!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
