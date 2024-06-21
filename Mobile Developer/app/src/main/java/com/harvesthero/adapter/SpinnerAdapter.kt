package com.harvesthero.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.harvesthero.R

class SpinnerAdapter(private val context: Context, private val items: List<SpinnerItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val item = getItem(position) as SpinnerItem

        val icon: ImageView = view.findViewById(R.id.spinnerIcon)
        val text: TextView = view.findViewById(R.id.spinnerText)

        icon.setImageResource(item.iconResId)
        text.text = item.text

        return view
    }
}

data class SpinnerItem(val iconResId: Int, val text: String)
