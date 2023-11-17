package com.example.memorycards.Presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorycards.Presentation.Modules.ModuleFragment
import com.example.memorycards.R
import com.example.memorycards.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openModuleButton.setOnClickListener{
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, ModuleFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}