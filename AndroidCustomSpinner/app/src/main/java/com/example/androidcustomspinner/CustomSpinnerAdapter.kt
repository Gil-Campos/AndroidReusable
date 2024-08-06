package com.example.androidcustomspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomSpinnerAdapter(context: Context, layout: Int, socialList: List<Pair<Int, String>>) :
    ArrayAdapter<Pair<Int, String>>(context, layout, socialList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initViewForDropDown(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val social = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_selected_item, parent, false)

        val selectedItemTitle = view.findViewById<TextView>(R.id.tvSelectedItemTitle)

        if (social != null) {
            selectedItemTitle.text = social.second
        }

        return view
    }

    private fun initViewForDropDown(position: Int, convertView: View?, parent: ViewGroup): View {
        val social = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item, parent, false)

        val icon = view.findViewById<ImageView>(R.id.ivItemIcon)
        val title = view.findViewById<TextView>(R.id.tvItemTitle)

        if (social != null) {
            icon.setImageResource(social.first)
            title.text = social.second
        }

        return view
    }
}