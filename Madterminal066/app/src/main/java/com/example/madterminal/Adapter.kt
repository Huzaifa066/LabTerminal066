package com.example.recyleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.madterminal.R
import kotlinx.android.synthetic.main.new_layout.view.*




class Adapter : RecyclerView.Adapter<viewholder>() {



    val title = arrayListOf<String>(
        "Name : Huzaifa , Registration : 066 , Phone:111",
        "Name : abc , Registration : 034 , Phone:222",
        "Name : def , Registration : 005 , Phone:333",
        "Name : ghi , Registration : 011 , Phone:000",
        "Name : jkl , Registration : 099 , Phone:555"


        )
    override fun getItemCount(): Int {
        return title.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {


        val linflater = LayoutInflater.from(parent?.context)
        val newinf = linflater.inflate(R.layout.new_layout,parent,false)
        return viewholder(newinf)


    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val titlev = title.get(position)
        holder.v.TextView.text=titlev
    }

}
class  viewholder(val v:View):RecyclerView.ViewHolder(v){

}

