package com.reel.tudu.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reel.tudu.databinding.LayoutCompletedTaskItemBinding
import com.reel.tudu.entities.TodoItem

class CompletedTaskRecyclerViewAdapter :
    ListAdapter<TodoItem, CompletedTaskRecyclerViewAdapter.ViewHolder>(CompletedTaskDiffCallback()) {

    inner class ViewHolder(private val binding: LayoutCompletedTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoItem) {
            binding.CompletedTaskName.text = todoItem.task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            LayoutCompletedTaskItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(getItem(position))
        }
    }
}


class CompletedTaskDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        oldItem == newItem

}