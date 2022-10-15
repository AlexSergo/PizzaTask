package com.example.testtask.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.databinding.PizzaItemBinding
import com.example.testtask.domain.models.Pizza
import com.squareup.picasso.Picasso

class PizzaAdapter() : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    private var pizza = mutableListOf<Pizza>()
    //захардкодил картинки, потому что от api картинки не работают (
    private val images : List<String> = listOf(
        "https://dubna.japanesesushi.ru/wp-content/uploads/2021/06/chetiri-sira1080x720-1080x720-1.jpg",
    "https://sushinadomahtari.ru/wp-content/uploads/2021/09/%D1%81-%D0%BB%D0%BE%D1%81%D0%BE%D1%81%D0%B5%D0%BC.png",
    "https://putilkovo-food.ru/image/catalog/Pizza/4-Sira/Food-1.jpg",
    "https://i8.photo.2gis.com/images/branch/46/6473924511530762_2544.jpg",
    "https://artsfood.ru/assets/images/your-kitchen/pizza-c-vetchinoi.png",
    "https://cdpiz1.pizzasoft.ru/pizzafab/items/3/margarita-srednyaya-main_image-3592-39487.jpg")

    fun set(pizza: List<Pizza>){
        this.pizza = pizza.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PizzaItemBinding.inflate(inflater,parent, false)
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        pizza.getOrNull(position)?.let { pizza->
            holder.binding.name.text = pizza.name
            holder.binding.title.text = pizza.title
            holder.binding.priceButton.text = pizza.price.toString()
            Picasso.get().load(images[position]).into(holder.binding.image)
        }
    }

    override fun getItemCount(): Int {
        return pizza.count()
    }

    class PizzaViewHolder(var binding: PizzaItemBinding): RecyclerView.ViewHolder(binding.root)
}