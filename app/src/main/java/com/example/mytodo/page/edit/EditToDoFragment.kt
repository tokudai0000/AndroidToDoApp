package com.example.mytodo.page.edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodo.R
import com.example.mytodo.databinding.EditTodoFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditToDoFragment:Fragment(R.layout.edit_todo_fragment) {
    private val args: EditToDoFragmentArgs by navArgs()

    private val vm: EditToDoViewModel by viewModels()

    private var _binding: EditTodoFragmentBinding? = null
    private val binding: EditTodoFragmentBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this._binding = EditTodoFragmentBinding.bind(view)

        val todo = args.todo
        binding.titleEdit.setText(todo.title)
        binding.detailEdit.setText(todo.detail)

        vm.errorMessage.observe(viewLifecycleOwner) { msg ->
            if (msg.isEmpty()) return@observe

            Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show()
            vm.errorMessage.value = "'"
        }
        vm.done.observe(viewLifecycleOwner) { todo ->
            setFragmentResult("edit", bundleOf("todo" to todo))
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                save()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        val title = binding.titleEdit.text.toString()
        val detail = binding.detailEdit.text.toString()

        vm.save(args.todo, title, detail)
    }

}