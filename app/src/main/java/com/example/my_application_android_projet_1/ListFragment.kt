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
import com.bumptech.glide.Glide

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list,
            container,
            false,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = List(100) {
            generateFakeProduct()
        }

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListAdapter(data, object : OnItemClickListener {
                override fun onItemClicked(product: Product) {
                    // TODO
                    findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToDetailsFragment(product)
                    )
                }

            })

        }


    }

}

class ListAdapter(private val data: List<Product>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<ListItem>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItem {
        return ListItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListItem, position: Int) {
        val product = data[position]
        holder.bindItem(product)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(product)
        }
    }

}

class ListItem(v: View) : RecyclerView.ViewHolder(v) {

    val name: TextView = v.findViewById(R.id.item_title)
    val brand: TextView = v.findViewById(R.id.item_brand)
    val image: ImageView = v.findViewById(R.id.item_image)

    fun bindItem(product: Product) {
        name.text = product.name
        brand.text = product.brand

        Glide.with(itemView).load(product.thumbnail).into(image)
    }


}

interface OnItemClickListener {
    fun onItemClicked(product: Product)
}