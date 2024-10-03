package com.example.hlist

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BottomLayoutListView: ConstraintLayout {

    lateinit var adapter: BottomLayoutListAdapter
    lateinit var recyclerviewItemList:RecyclerView
    init {
        ConstraintLayout.inflate(context, R.layout.layout_bottom_list_view, this)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setup(context: Context,
              itemList: ArrayList<BaseModeModel.BottomLayoutItem>,
              onItemClick: ((BaseModeModel.BottomLayoutItem) -> Unit)?=null,
              onLongClick: ((BaseModeModel.BottomLayoutItem) -> Unit)?=null){

        recyclerviewItemList = findViewById<RecyclerView>(R.id.recyclerView_item_list)

        var showItemNo = 5
        if (itemList.size < showItemNo)
            showItemNo = itemList.size

        adapter = BottomLayoutListAdapter(context)
        recyclerviewItemList.adapter = adapter
        //recyclerView_port_list.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerviewItemList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.setList(itemList)
        adapter.onItemClick = onItemClick
        adapter.onItemLongClick = onLongClick
    }

    fun addItem(item:BaseModeModel.BottomLayoutItem){
        adapter.addItem(item)
    }

    fun insertItem(item:BaseModeModel.BottomLayoutItem, pos:Int){
        adapter.insertItem(item, pos)
    }

    fun getItems() : ArrayList<BaseModeModel.BottomLayoutItem>{
        return adapter.itemList
    }
}