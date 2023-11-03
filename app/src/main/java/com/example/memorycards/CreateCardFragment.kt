package com.example.memorycards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorycards.databinding.FragmentCreateCardBinding
import com.example.memorycards.databinding.FragmentMainBinding

class CreateCardFragment : Fragment(R.layout.fragment_create_card) {

    val binding by viewBinding(FragmentCreateCardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}