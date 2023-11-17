package com.example.memorycards.Presentation.Modules

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorycards.Application.App
import com.example.memorycards.Base.Factory
import com.example.memorycards.Presentation.EditModule.EditModuleFragment
import com.example.memorycards.items.asItem
import com.example.memorycards.Presentation.adapters.ModuleAdapter
import com.example.memorycards.R
import com.example.memorycards.databinding.FragmentModuleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModuleFragment : Fragment(R.layout.fragment_module) {

    val binding by viewBinding(FragmentModuleBinding::bind)

    private val viewModel by viewModels<ModuleViewModel> {
        Factory {
            (requireActivity().application as App)
                .getAppComponent()
                .getModuleVIewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editModuleButton.setOnClickListener{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, EditModuleFragment())
                .addToBackStack(null)
                .commit()
        }
        val adapter = ModuleAdapter()
        binding.cards.adapter = adapter
        val layout = LinearLayoutManager(context).apply { orientation = RecyclerView.HORIZONTAL }
        //layout.orientation = RecyclerView.HORIZONTAL
        binding.cards.layoutManager = layout

        CoroutineScope(Dispatchers.IO).launch {
            val items = viewModel.getWord().map{it.asItem()}

            withContext(Dispatchers.Main) {
                adapter.submitList(items)
            }
        }

    }
}