package com.example.recyclerview

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val myList: List<MyItems>,
                  private val listener:OnItemClickListener
):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_list,
        parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=myList[position]
        holder.imageView.setImageResource(currentItem.myImages)
        holder.textView1.text=currentItem.text1
        holder.textView2.text=currentItem.text2

    }

    override fun getItemCount()=myList.size

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val imageView: ImageView=itemView.findViewById(R.id.image_view)
        val textView1: TextView=itemView.findViewById(R.id.text1)
        val textView2: TextView=itemView.findViewById(R.id.text2)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(viewType: View?) {
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}