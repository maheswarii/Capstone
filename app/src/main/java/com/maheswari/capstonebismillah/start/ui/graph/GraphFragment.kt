package com.maheswari.capstonebismillah.start.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maheswari.capstonebismillah.R
import com.maheswari.capstonebismillah.start.R

class GraphFragment : Fragment() {

    private lateinit var graphViewModel: GraphViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        graphViewModel =
            ViewModelProvider(this).get(GraphViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_graph, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        graphViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}