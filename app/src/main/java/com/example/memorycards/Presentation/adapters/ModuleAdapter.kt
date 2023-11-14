package com.example.memorycards.Presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.memorycards.Presentation.Resources.WordItem
import com.example.memorycards.R
import com.example.memorycards.databinding.ModuleLayoutBinding

class ModuleAdapter(): ListAdapter<WordItem, ModuleAdapter.ViewHolder>(Diffutil()) {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.module_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = ModuleLayoutBinding.bind(holder.itemView)
        val item =currentList[position]
        binding.root.setOnClickListener{
            item.isTranslate = !item.isTranslate
            binding.cardName.text = if (item.isTranslate) {
                item.name
            } else {
                item.translation
            }

        }
        binding.cardName.text = item.name
    }
}
class Diffutil() : DiffUtil.ItemCallback<WordItem>() {
    override fun areItemsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
        return oldItem == newItem
    }
}