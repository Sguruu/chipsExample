package com.weather.hipsexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.hipsexample.model.ModelChips

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<ModelChips>()

        list.addAll(generateModel())

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.adapter = AdapterRecycler(list) { cellActive, textView, cardView ->
            elementChecked(cellActive, textView, cardView)
        }
    }

    private fun elementChecked(
        cellActive: Boolean,
        textView: TextView,
        cardView: CardView
    ) {
        when (cellActive) {
            true -> {
                textView.setTextColor(resources.getColor(R.color.color_text_2, null))
                cardView.setCardBackgroundColor(
                    resources.getColor(
                        R.color.color_cardWie_2,
                        null
                    )
                )
            }
            false -> {
                textView.setTextColor(resources.getColor(R.color.black, null))
                cardView.setCardBackgroundColor(resources.getColor(R.color.white, null))
            }
        }
    }

    private fun generateModel(): MutableList<ModelChips> {
        val list = mutableListOf<ModelChips>()
        for (i in 0..14) {
            list.add(ModelChips("Категория $i"))
        }
        return list
    }
}
