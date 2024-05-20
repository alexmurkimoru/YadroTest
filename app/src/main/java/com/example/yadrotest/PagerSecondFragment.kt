package com.example.yadrotest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf


class PagerSecondFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentInfo = arguments?.getSerializable(ARG_SOURCE) as DataSource
        view.findViewById<TextView>(R.id.app_temp_text).text = currentInfo.apparentTem + "°C"
        view.findViewById<TextView>(R.id.humidity_text).text = currentInfo.humidity + " %"
        view.findViewById<TextView>(R.id.wind_speed_text).text = currentInfo.windSpeed + " km/h"
    }

    companion object {

        const val ARG_SOURCE = "arg_source"
        @JvmStatic
        fun newInstance(dataSource: DataSource) =
            PagerSecondFragment().apply {
                arguments = bundleOf(ARG_SOURCE to dataSource )
            }
    }
}