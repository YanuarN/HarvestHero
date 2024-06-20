package com.harvesthero.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harvesthero.R
import com.harvesthero.data.Product
import com.harvesthero.adapter.ProductAdapter

class SearchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns

        // Initialize product list and add sample data
        productList = mutableListOf(
            Product("Pupuk Sakti", "Menyuburkan tanaman", R.drawable.pupuk, "https://www.tokopedia.com/pupuk-sakti"),
            Product("Peptisida Maut", "Mengurangi hama pada padi", R.drawable.peptisida, "https://www.tokopedia.com/peptisida-maut"),
            Product("Peptisida Maut", "Mengurangi hama pada padi", R.drawable.peptisida, "https://www.tokopedia.com/peptisida-maut"),
            Product("Pupuk Sakti", "Menyuburkan tanaman", R.drawable.pupuk, "https://www.tokopedia.com/pupuk-sakti"),
            Product("Peptisida Maut", "Mengurangi hama pada padi", R.drawable.peptisida, "https://www.tokopedia.com/peptisida-maut"),
            Product("Pupuk Sakti", "Menyuburkan tanaman", R.drawable.pupuk, "https://www.tokopedia.com/pupuk-sakti")
        )

        // Set adapter
        productAdapter = ProductAdapter(this, productList)
        recyclerView.adapter = productAdapter
    }
}