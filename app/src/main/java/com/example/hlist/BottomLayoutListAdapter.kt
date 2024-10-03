package com.example.hlist

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView


class BottomLayoutListAdapter(private val context: Context): RecyclerView.Adapter<BottomLayoutListAdapter.BottomLayoutListViewHolder>()  {
    var itemList = ArrayList<BaseModeModel.BottomLayoutItem>()
    var onItemClick: ((BaseModeModel.BottomLayoutItem) -> Unit)?=null
    var onItemLongClick: ((BaseModeModel.BottomLayoutItem) -> Unit)?=null
    inner class BottomLayoutListViewHolder(itemView: View,
                                     onItemClick: ((BaseModeModel.BottomLayoutItem) -> Unit)?=null,
                                     onItemLongClick: ((BaseModeModel.BottomLayoutItem) -> Unit)?=null) : RecyclerView.ViewHolder(itemView) {

        private var currentItem: BaseModeModel.BottomLayoutItem? = null

        val nameTextView: TextView = itemView.findViewById(R.id.textView_name)
        val imgView: ImageView = itemView.findViewById(R.id.imageView_item)
        val itemLayout: LinearLayout = itemView.findViewById(R.id.layout_item)
        init {
            itemLayout.setOnClickListener {
                currentItem?.let {
                    onItemClick?.invoke(it)
                }
            }

            itemView.isLongClickable = true
            itemView.setOnLongClickListener {
                currentItem?.let {
                    onItemLongClick?.invoke(it)

                }

//                showPopupMenu(it, 0)
                false
            }
        }

        fun bind(item: BaseModeModel.BottomLayoutItem){
            currentItem = item

            nameTextView.text = item.name
            imgView.setImageResource(item.image)
        }

        private fun showPopupMenu(view: View, position: Int) {
            // Create the PopupMenu
            val popup = PopupMenu(context, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_bottom_layout_item, popup.menu)

            // Handle popup menu item clicks
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_edit -> {
                        // Handle Edit action
                        println("edit selected")
                        true
                    }
                    R.id.action_delete -> {
                        // Handle Delete action
                        println("delete selected")
                        true
                    }
                    else -> false
                }
            }

            // Show the popup menu
            popup.show()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomLayoutListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_bottom_list_item, parent, false)
        return BottomLayoutListViewHolder(layout, onItemClick, onItemLongClick)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: BottomLayoutListViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setList(list: ArrayList<BaseModeModel.BottomLayoutItem>){
        this.itemList = list

        Handler(Looper.getMainLooper())?.post(Runnable{
            this.notifyDataSetChanged()
        })
    }

    fun updateItem(item: BaseModeModel.BottomLayoutItem, pos:Int){
        this.itemList[pos] = item

        Handler(Looper.getMainLooper())?.post(Runnable{
            this.notifyItemChanged(pos)
        })
    }

    fun addItem(item: BaseModeModel.BottomLayoutItem){
        this.itemList.add(item)
        notifyItemInserted(this.itemList.size - 1)
    }

    fun insertItem(item: BaseModeModel.BottomLayoutItem, pos:Int){
        this.itemList.add(pos, item)
        notifyItemInserted(pos)
    }
}