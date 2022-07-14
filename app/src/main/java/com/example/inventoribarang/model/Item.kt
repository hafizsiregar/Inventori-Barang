package com.example.inventoribarang.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Item")
@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nama")
    var name: String? = "",

    @ColumnInfo(name = "satuan")
    var unit: String? = "",

    @ColumnInfo(name = "jumlah_barang")
    var quantity: Int? = 0,

    @ColumnInfo(name = "harga")
    var price: Int? = 0,

    @ColumnInfo(name = "nama_pemasok")
    var supplier: String? = "",

    @ColumnInfo(name = "keterangan")
    var description: String? = "",

    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable