package com.example.inventoribarang.sources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventoribarang.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ItemRoomDatabase {
            if (INSTANCE == null) {
                synchronized(ItemRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ItemRoomDatabase::class.java, "item_database")
                            .build()
                }
            }
            return INSTANCE as ItemRoomDatabase
        }
    }
}