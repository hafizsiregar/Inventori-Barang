package com.example.inventoribarang.features.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.inventoribarang.model.Item

class ItemDiffCallback(private val mOldItemList: List<Item>, private val mNewItemList: List<Item>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldItemList.size
    }

    override fun getNewListSize(): Int {
        return mNewItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldItemList[oldItemPosition].id == mNewItemList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldItemList[oldItemPosition]
        val newEmployee = mNewItemList[newItemPosition]
        return oldEmployee.name == newEmployee.name
                && oldEmployee.unit == newEmployee.unit
                && oldEmployee.quantity == newEmployee.quantity
                && oldEmployee.price ==newEmployee.price
                && oldEmployee.supplier == newEmployee.supplier
                && oldEmployee.description == newEmployee.description
    }
}