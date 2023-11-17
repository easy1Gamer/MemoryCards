package com.example.memorycards.Presentation.EditModule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorycards.Application.App
import com.example.memorycards.Base.Factory
import com.example.memorycards.items.asItem
import com.example.memorycards.Presentation.adapters.EditModuleAdapter
import com.example.memorycards.R
import com.example.memorycards.databinding.FragmentEditModuleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.memorycards.Presentation.callbacks.SwipeToDeleteCallback
import kotlinx.coroutines.flow.collect

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

        lifecycleScope.launch {
            viewModel.uistate().collect {
                adapter.submitList(it)
            }
        }

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteWord(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.editcards)
    }


    private fun onClickSave() {
        val name = binding.nameEditText.text?.toString().orEmpty()
        val translation = binding.translationEditText.text?.toString().orEmpty()

        binding.nameInputLayout.error = getString(R.string.empty_word_error).takeIf { name.isEmpty() }
        binding.translationInputLayout.error = getString(R.string.empty_translation_error).takeIf { translation.isEmpty() }

        if (name.isNotEmpty() && translation.isNotEmpty()) {
            viewModel.clickSave(name, translation)
            binding.nameEditText.text = null
            binding.translationEditText.text = null
        }
    }
}

