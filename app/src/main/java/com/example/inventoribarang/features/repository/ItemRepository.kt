package com.example.inventoribarang.features.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.inventoribarang.model.Item
import com.example.inventoribarang.sources.ItemDao
import com.example.inventoribarang.sources.ItemRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ItemRepository(application: Application) {
    private val mItemsDao: ItemDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = ItemRoomDatabase.getDatabase(application)
        mItemsDao = db.noteDao()
    }

    fun getAllItems(): LiveData<List<Item>> = mItemsDao.getAllNotes()

    fun insert(note: Item) {
        executorService.execute { mItemsDao.insert(note) }
    }

    fun delete(note: Item) {
        executorService.execute { mItemsDao.delete(note) }
    }

    fun update(note: Item) {
        executorService.execute { mItemsDao.update(note) }
    }
}