package com.reel.tudu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reel.tudu.databinding.LayoutTodoItemBinding
import com.reel.tudu.entities.TodoItem

class HomeRecyclerViewAdapter :
    ListAdapter<TodoItem, HomeRecyclerViewAdapter.ViewHolder>(TodoItemDiffCallback()) {

    inner class ViewHolder(private val binding: LayoutTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoItem) {
            binding.TodoTask.text = todoItem.task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            LayoutTodoItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(getItem(position))
        }
    }


}

class TodoItemDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        oldItem == newItem

}
