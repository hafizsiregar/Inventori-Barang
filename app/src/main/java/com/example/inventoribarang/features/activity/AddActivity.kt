package com.example.inventoribarang.features.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.inventoribarang.features.helper.DateHelper
import com.example.inventoribarang.viewmodel.ViewModelFactory
import com.example.inventoribarang.model.Item
import com.example.inventoribarang.R
import com.example.inventoribarang.databinding.ActivityAddBinding
import com.example.inventoribarang.viewmodel.ItemAddUpdateViewModel

class AddActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE = "extra_item"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var item: Item? = null
    private lateinit var itemAddUpdateViewModel: ItemAddUpdateViewModel

    private var _binding: ActivityAddBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        itemAddUpdateViewModel = obtainViewModel(this@AddActivity)

        item = intent.getParcelableExtra(EXTRA_NOTE)
        if (item != null) {
            isEdit = true
        } else {
            item = Item()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (item != null) {
                item?.let { item ->
                    binding?.etName?.setText(item.name)
                    binding?.etUnit?.setText(item.unit)
                    binding?.etQuantity?.setText(item.quantity.toString())
                    binding?.etPrice?.setText(item.price.toString())
                    binding?.etSupplier?.setText(item.supplier)
                    binding?.etDesc?.setText(item.description)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.btnInsert?.text = btnTitle
        binding?.btnInsert?.setOnClickListener {
            val title = binding?.etName?.text.toString().trim()
            val unit = binding?.etUnit?.text.toString().trim()
            val quantity = binding?.etQuantity?.text.toString().trim()
            val price = binding?.etPrice?.text.toString().trim()
            val supplier = binding?.etSupplier?.text.toString().trim()
            val desc = binding?.etDesc?.text.toString().trim()
            when {
                title.isEmpty() -> {
                    binding?.etName?.error = getString(R.string.empty)
                }
                unit.isEmpty() -> {
                    binding?.etUnit?.error = getString(R.string.empty)
                }
                quantity.isEmpty() -> {
                    binding?.etQuantity?.error = getString(R.string.empty)
                }
                price.isEmpty() -> {
                    binding?.etPrice?.error = getString(R.string.empty)
                }
                supplier.isEmpty() -> {
                    binding?.etSupplier?.error = getString(R.string.empty)
                }
                desc.isEmpty() -> {
                    binding?.etDesc?.error = getString(R.string.empty)
                }
                else -> {
                    item.let { item ->
                        item?.name = title
                        item?.unit = unit
                        item?.quantity = quantity.toIntOrNull() ?: 0
                        item?.price = price.toIntOrNull() ?: 0
                        item?.supplier = supplier
                        item?.description = desc
                    }
                    if (isEdit) {
                        itemAddUpdateViewModel.update(item as Item)
                        showToast(getString(R.string.changed))
                    } else {
                        item.let { item ->
                            item?.date = DateHelper.getCurrentDate()
                        }
                        itemAddUpdateViewModel.insert(item as Item)
                        showToast(getString(R.string.added))
                    }
                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    itemAddUpdateViewModel.delete(item as Item)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): ItemAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(ItemAddUpdateViewModel::class.java)
    }
}