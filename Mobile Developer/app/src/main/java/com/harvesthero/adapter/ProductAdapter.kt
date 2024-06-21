package com.harvesthero.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harvesthero.R
import com.harvesthero.data.Product

class ProductAdapter(private val context: Context, private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productDescription: TextView = itemView.findViewById(R.id.product_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productDescription.text = product.description
        holder.productImage.setImageResource(product.imageResourceId)

        // Set OnClickListener on specific views
        holder.productImage.setOnClickListener {
            openProductUrl(product.url)
        }

        holder.productName.setOnClickListener {
            openProductUrl(product.url)
        }

        holder.productDescription.setOnClickListener {
            openProductUrl(product.url)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    private fun openProductUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}