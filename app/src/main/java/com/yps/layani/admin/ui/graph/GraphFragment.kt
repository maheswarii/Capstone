package com.yps.layani.admin.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yps.layani.R

class GraphFragment : Fragment() {

    //private lateinit var graphViewModel: GraphViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        graphViewModel =
//            ViewModelProvider(this).get(GraphViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_graph, container, false)
//        val textView: TextView = root.findViewById(R.id.nav_graph)
//        graphViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root

        return inflater.inflate(R.layout.fragment_graph, container, false)
    }
}