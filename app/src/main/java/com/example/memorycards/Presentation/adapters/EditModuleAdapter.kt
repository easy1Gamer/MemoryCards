package com.example.memorycards.Presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memorycards.items.WordItem
import com.example.memorycards.R
import com.example.memorycards.databinding.EditcardsLayoutBinding

class EditModuleAdapter(private val onClickListener : (WordItem)-> Unit) : ListAdapter<WordItem, EditModuleAdapter.ViewHolder>(Diffutil()) {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.editcards_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = EditcardsLayoutBinding.bind(holder.itemView)
        val item = currentList[position]
        binding.editableName.text = item.name
        binding.editableTranslation.text = item.translation
        binding.root.setOnClickListener{
            onClickListener.invoke(item)
        }
    }
    private class Diffutil() : DiffUtil.ItemCallback<WordItem>() {
        override fun areItemsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
            return oldItem == newItem
        }
    }
}


