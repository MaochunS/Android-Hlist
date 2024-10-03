package com.example.hlist

class BaseModeModel {

    enum class BottomLayoutItemType(var desc: String) {
        item("Item"),
        addButton("AddButton")
    }
    data class BottomLayoutItem(val index: Int = 0,
                                var name: String = "",
                                val type: BottomLayoutItemType = BottomLayoutItemType.item,
                                var image: Int = 0)
}