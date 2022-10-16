package com.weather.hipsexample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.hipsexample.model.Category
import com.weather.hipsexample.model.ModelChips
import com.weather.hipsexample.model.ModelProduct

class MainActivity : AppCompatActivity() {

    private val listChips = mutableListOf<ModelChips>()
    private val listProduct = mutableListOf<ModelProduct>()
    private var recyclerViewChips: RecyclerView? = null
    private var recyclerViewProduct: RecyclerView? = null
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewChips = findViewById(R.id.recyclerViewChips)
        recyclerViewProduct = findViewById(R.id.recyclerViewProduct)
        button = findViewById(R.id.buttonA)

        listChips.addAll(generateModelChips())
        listProduct.addAll(generateModelProduct())

        createProductRecycler()
        createChipsRecycler()

        button?.setOnClickListener {
            recyclerViewProduct?.smoothScrollToPosition(34)
        }
    }

    private fun createProductRecycler() {
        recyclerViewProduct?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewProduct?.adapter = AdapterRecyclerProduct(listProduct)
    }

    private fun createChipsRecycler() {
        recyclerViewChips?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewChips?.adapter =
            AdapterRecyclerChips(listChips) { cellActive, textView, cardView, category ->
                elementChecked(cellActive, textView, cardView)
                category?.let {
                    scrollProduct(it)
                }
            }
    }

    private fun scrollProduct(category: Category) {
        if (listProduct.isNotEmpty()) {
            listProduct.forEachIndexed { index, modelProduct ->
                if (modelProduct.category == category) {
                    recyclerViewProduct?.smoothScrollToPosition(index)
                    return
                }
            }
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

    private fun generateModelProduct(): MutableList<ModelProduct> {
        val list = mutableListOf<ModelProduct>()
        for (i in 0..100) {
            if (i < 20) {
                list.add(
                    ModelProduct(
                        "${Category.FISH.nameCategory} $i",
                        "Описание $i",
                        Category.FISH
                    )
                )
            } else if (i < 60) {
                list.add(
                    ModelProduct(
                        "${Category.MEAT.nameCategory} $i",
                        "Описание $i",
                        Category.MEAT
                    )
                )
            } else {
                list.add(
                    ModelProduct(
                        "${Category.PIZZA.nameCategory} $i",
                        "Описание $i",
                        Category.PIZZA
                    )
                )
            }
        }
        return list
    }

    private fun generateModelChips(): MutableList<ModelChips> {
        val list = mutableListOf<ModelChips>()
        list.add(ModelChips(Category.FISH.nameCategory, Category.FISH))
        list.add(ModelChips(Category.MEAT.nameCategory, Category.MEAT))
        list.add(ModelChips(Category.PIZZA.nameCategory, Category.PIZZA))
        return list
    }
}
