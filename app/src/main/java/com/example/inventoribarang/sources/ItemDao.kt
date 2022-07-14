package com.example.inventoribarang.sources

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.inventoribarang.model.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Item)

    @Update
    fun update(note: Item)

    @Delete
    fun delete(note: Item)

    @Query("SELECT * from item ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Item>>
}