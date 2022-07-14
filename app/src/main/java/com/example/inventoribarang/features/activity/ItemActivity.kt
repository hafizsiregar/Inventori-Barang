package com.example.inventoribarang.features.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventoribarang.viewmodel.ViewModelFactory
import com.example.inventoribarang.R
import com.example.inventoribarang.databinding.ActivityItemBinding
import com.example.inventoribarang.viewmodel.MainViewModel
import com.example.inventoribarang.features.adapter.ItemAdapter

class ItemActivity : AppCompatActivity() {

    private var _binding: ActivityItemBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val mainViewModel = obtainViewModel(this@ItemActivity)
        mainViewModel.getAllItems().observe(this, { itemList ->
            if (itemList != null) {
                adapter.setListItems(itemList)
            }
        })

        adapter = ItemAdapter()

        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@ItemActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}