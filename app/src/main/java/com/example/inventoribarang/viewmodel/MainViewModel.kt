package com.example.inventoribarang.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.inventoribarang.model.Item
import com.example.inventoribarang.features.repository.ItemRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mItemRepository: ItemRepository = ItemRepository(application)
    fun getAllItems(): LiveData<List<Item>> = mItemRepository.getAllItems()
}