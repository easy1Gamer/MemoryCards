package com.example.memorycards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorycards.databinding.FragmentModuleBinding

class ModuleFragment : Fragment(R.layout.fragment_module) {

    val binding by viewBinding(FragmentModuleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newCardButton.setOnClickListener{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, CreateCardFragment())
                .commit()
        }
    }
}