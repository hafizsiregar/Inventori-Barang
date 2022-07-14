package com.example.inventoribarang.features.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoribarang.model.Item
import com.example.inventoribarang.features.helper.ItemDiffCallback
import com.example.inventoribarang.databinding.ItemNoteBinding
import com.example.inventoribarang.features.activity.AddActivity
import java.util.*

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val listItems = ArrayList<Item>()
    fun setListItems(listItems: List<Item>) {
        val diffCallback = ItemDiffCallback(this.listItems, listItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listItems.clear()
        this.listItems.addAll(listItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    inner class ItemViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                tvItemTitle.text = item.name
                tvItemUnit.text = item.unit
                tvItemQuantity.text = item.quantity.toString()
                tvItemPrice.text = item.price.toString()
                tvItemSupplier.text = item.supplier
                tvItemDescription.text = item.description
                tvItemDate.text = item.date
                cvItemList.setOnClickListener {
                    val intent = Intent(it.context, AddActivity::class.java)
                    intent.putExtra(AddActivity.EXTRA_NOTE, item)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}