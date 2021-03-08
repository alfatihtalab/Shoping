package com.alfatih.shoping.home.testFragment.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alfatih.shoping.R
import com.alfatih.shoping.database.Product

class ProductAdabter(val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdabter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView
        var textTitle: TextView
        var desc: TextView

        init {
            imageView = itemView.findViewById(R.id.productImage)
            textTitle = itemView.findViewById(R.id.product_title)
            desc = itemView.findViewById(R.id.subTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.imageView.setImageResource(productList[position].imageView)
        holder.textTitle.text = productList[position].title
        holder.desc.text = productList[position].desc
    }

    override fun getItemCount(): Int = productList.size
}