package com.example.my_application_android_projet_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_application_android_projet_1.Series as Series1

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list_item,
            container,
            false,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = List(100) {
            generateFakeSerie()
        }

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListAdapter(data, object : OnItemClickListener {
                override fun onItemClicked(series: Series1) {
                    // TODO
                    var product = null
                    findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToDetailsFragment(product)
                    )
                }

                })

        }


    }

}

class ListAdapter(private val data: List<Series1>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<ListItem>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItem {
        return ListItem(
            LayoutInflater.from(parent.context)
                //.inflate(R.layout.list_item, parent, false)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListItem, position: Int) {
        val series = data[position]
        //holder.bindItem(series)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(series)
        }
    }

}

class ListItem(v: View) : RecyclerView.ViewHolder(v) {
    val name: TextView = v.findViewById(R.id.retour)
    val image: ImageView = v.findViewById(R.id.titre)
    val prodtv: ImageView = v.findViewById(R.id.prodtv)
    val proddate: ImageView = v.findViewById(R.id.proddate)
    val prodepis: ImageView = v.findViewById(R.id.prodepis)
}
/*
    fun bindItem(series: Series) {
        name.text = series.name
    }*/



interface OnItemClickListener {
    fun onItemClicked(series: Series1)
}