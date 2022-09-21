package com.example.mytodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TodoItemBinding
import com.example.mytodo.model.todo.ToDo
import android.text.format.DateFormat

class ToDoAdapter(
    private val listener: (ToDo) -> Unit
): ListAdapter<ToDo, ToDoAdapter.ViewHolder>(
    callbacks
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)

        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val todo = getItem(position)
            listener(todo)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bindTo(todo)
    }

    class ViewHolder (
        private val binding: TodoItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindTo(todo: ToDo) {
            binding.titleText.text = todo.title
            binding.createdText.text = DateFormat.format(
                "yyyy-MM-dd hh:mm:ss",
                todo.created
            )
        }
    }

    companion object {
        private val callbacks = object: DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.created == newItem.created
            }
        }
    }
}