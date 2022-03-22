package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener
{

    private val numberOfItems=generateDummyList(size = 20)
    private val adapter=ItemAdapter(numberOfItems,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewR=findViewById<RecyclerView>(R.id.recycler_view)

        viewR.adapter=adapter
        viewR.layoutManager=LinearLayoutManager(this)
        viewR.setHasFixedSize(true)

    }

    fun insertItem(view: View){
        val index:Int= Random.nextInt(until = 8)
        val newItem=MyItems(
            R.drawable.apple,
            text1="My new item $index",
            text2 = "Second line"
        )
        numberOfItems.add(index,newItem)
        adapter.notifyItemInserted(index)
    }
    fun deleteItem(view: View){
        val index:Int= Random.nextInt(until = 8)

        numberOfItems.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this,"Item $position clicked",
            Toast.LENGTH_SHORT).show()
        val clickedItem=numberOfItems[position]
        clickedItem.text1="Clicked"
        adapter.notifyItemChanged(position)

    }

    fun generateDummyList(size:Int):ArrayList<MyItems>{
        val list=ArrayList<MyItems>()
        for (i in 0 until size){
            val drawable=when(i%7){
                0->R.drawable.apple
                1->R.drawable.infinix
                2-> R.drawable.itel
                3-> R.drawable.nokia
                4->R.drawable.samsung
                5->R.drawable.oppo
                else->R.drawable.tecno
            }
            val item=MyItems(drawable,"This is $i","Text 2")
            list+=item
        }
        return list
    }
}

