package com.weather.hipsexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.weather.hipsexample.model.Category
import com.weather.hipsexample.model.ModelChips

class AdapterRecyclerChips(
    private val list: List<ModelChips>,
    private val callbackElementChecked: (Boolean, TextView, CardView, Category?) -> Unit
) : RecyclerView.Adapter<AdapterRecyclerChips.ViewHolder>() {

    private var elementChecked: Int? = null
    private var elementCheckedOld: Int? = null
    private var currentCategory: Category? = null

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
            currentCategory = list[holder.adapterPosition].category

            if (elementCheckedOld == null) {
                elementCheckedOld = elementChecked
            }

            // перересовка конкретного элемента
            redrawing()
        }

        elementChecked?.let {
            if (holder.textView != null &&
                holder.cardView != null
            ) {
                val cellActive = it == holder.adapterPosition
                callbackElementChecked.invoke(
                    cellActive,
                    holder.textView!!,
                    holder.cardView!!,
                    if (cellActive) currentCategory else null
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun redrawing() {
        notifyItemChanged(elementChecked!!)
        notifyItemChanged(elementCheckedOld!!)
    }
}
