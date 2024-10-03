package com.example.hlist

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    var total = 5
    lateinit var bottomList: BottomLayoutListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val menuBtn = findViewById<Button>(R.id.button_menu)
        menuBtn.setOnClickListener {
            showPopupMenu(it)
        }

        bottomList = findViewById(R.id.item_list)

        val itemArr = ArrayList<BaseModeModel.BottomLayoutItem>()
        for (i in 1..total){
            itemArr.add(BaseModeModel.BottomLayoutItem(i, "Item$i"))
        }

        itemArr.add(BaseModeModel.BottomLayoutItem(111, "Item$111", BaseModeModel.BottomLayoutItemType.addButton))
        bottomList.setup(this, itemArr, onItemClick, onItemLongClick)

        registerForContextMenu(bottomList.recyclerviewItemList)
    }

    private fun showPopupMenu(view: View) {
        // Create a PopupMenu, specifying the anchor view (the button in this case)
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        // Handle menu item clicks
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_setting -> {
                    Toast.makeText(this, "Edit Selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_delete -> {
                    Toast.makeText(this, "Delete Selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Show the popup menu
        popupMenu.show()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // Inflate context menu items
        menuInflater.inflate(R.menu.menu_main, menu)
        menu?.setHeaderTitle("Select Action")
    }

    // Handle context menu item clicks
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {
                Toast.makeText(this, "Setting Selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_delete -> {
                Toast.makeText(this, "Delete Selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private val onItemClick: (BaseModeModel.BottomLayoutItem) -> Unit = {
        if (it.type == BaseModeModel.BottomLayoutItemType.addButton){
            total += 1
//            itemArr.add(BaseModeModel.BottomLayoutItem(total, "Item$total", BaseModeModel.BottomLayoutItemType.addButton))
//            bottomList.addItem(BaseModeModel.BottomLayoutItem(total, "Item$total", BaseModeModel.BottomLayoutItemType.addButton))
            val item = BaseModeModel.BottomLayoutItem(total, "Item$total", BaseModeModel.BottomLayoutItemType.addButton)
            bottomList.insertItem(item, bottomList.getItems().size-1)
        }else{
            println("click item ${it.index}")
        }
    }

    private val onItemLongClick: (BaseModeModel.BottomLayoutItem) -> Unit = {
        if (it.type == BaseModeModel.BottomLayoutItemType.addButton){

        }else{
            println("Long click item ${it.index}")
        }
    }
}