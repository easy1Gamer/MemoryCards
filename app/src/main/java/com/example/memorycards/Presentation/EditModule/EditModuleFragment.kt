package com.example.memorycards.Presentation.EditModule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorycards.Application.App
import com.example.memorycards.Base.Factory
import com.example.memorycards.Presentation.Resources.asItem
import com.example.memorycards.Presentation.adapters.EditModuleAdapter
import com.example.memorycards.R
import com.example.memorycards.databinding.FragmentEditModuleBinding
import com.example.memorycards.databinding.FragmentModuleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.memorycards.Presentation.Resources.SwipeToDeleteCallback

class EditModuleFragment : Fragment(R.layout.fragment_edit_module) {

    val binding by viewBinding(FragmentEditModuleBinding::bind)

    private val viewModel by viewModels<EditModuleViewModel> {
        Factory {
            (requireActivity().application as App)
                .getAppComponent()
                .editModuleVIewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EditModuleAdapter()
        binding.editcards.adapter = adapter
        val layout = LinearLayoutManager(context)
        binding.editcards.layoutManager = layout
        binding.save.setOnClickListener {
            onClickSave()
        }

        updateItems()

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteWord(item.id)

                updateItems()

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.editcards)
    }

    fun updateItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val items = viewModel.getWord().map { it.asItem() }

            withContext(Dispatchers.Main) {
                (binding.editcards.adapter as EditModuleAdapter).submitList(items)
            }
        }
    }

    private fun onClickSave() {
        val name = binding.nameEditText.text?.toString().orEmpty()
        val translation = binding.translationEditText.text?.toString().orEmpty()

        binding.nameInputLayout.error = "Слово не должно быть пустым".takeIf { name.isEmpty() }
        binding.translationInputLayout.error = "Перевод не должен быть пустым".takeIf { translation.isEmpty() }

        if (name.isNotEmpty() && translation.isNotEmpty()) {
            viewModel.clickSave(name, translation)

            updateItems()

        }
    }
}

