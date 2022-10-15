package com.weather.hipsexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.hipsexample.model.ModelChips

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val list = mutableListOf<ModelChips>()

        for (i in 0..14) {
            list.add(ModelChips("Категория $i"))
        }

        recyclerView?.adapter = AdapterRecycler(list) { cellActive, textView, cardView ->
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
    }
}
