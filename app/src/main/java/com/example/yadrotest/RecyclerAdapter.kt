package com.example.yadrotest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yadrotest.databinding.RecyclerItemBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.WeatherHolder>() {
    private var listOfForecast = listOf<ForecastItem>()

    class WeatherHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerItemBinding.bind(item)

        fun bind(item: ForecastItem) = with(binding) {
            dateText.text = item.date
            tempText.text = item.minTemp + " / " + item.maxTemp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return WeatherHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(listOfForecast[position])
    }

    override fun getItemCount() = listOfForecast.size

    fun setSource(sourceList: List<ForecastItem>){
        listOfForecast = sourceList
        notifyDataSetChanged()
    }

}

