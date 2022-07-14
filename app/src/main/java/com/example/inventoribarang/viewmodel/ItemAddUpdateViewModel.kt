package com.example.inventoribarang.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.inventoribarang.model.Item
import com.example.inventoribarang.features.repository.ItemRepository

class ItemAddUpdateViewModel(application: Application) : ViewModel() {

    private val mItemRepository: ItemRepository = ItemRepository(application)

    fun insert(note: Item) {
        mItemRepository.insert(note)
    }

    fun update(note: Item) {
        mItemRepository.update(note)
    }

    fun delete(note: Item) {
        mItemRepository.delete(note)
    }

}