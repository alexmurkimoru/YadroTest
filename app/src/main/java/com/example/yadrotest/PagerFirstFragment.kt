package com.example.yadrotest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView


class PagerFirstFragment : Fragment() {

    private val adapter = RecyclerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycle_view)
        recycler.adapter = adapter
        (arguments?.getSerializable(ARG_SOURCE) as DataSource).forecast?.let { adapter.setSource(it) }

    }

    companion object {

       const val ARG_SOURCE = "arg_source"
        @JvmStatic
        fun newInstance(dataSource: DataSource) =
            PagerFirstFragment().apply {
                arguments = bundleOf(ARG_SOURCE to dataSource )
            }
    }
}