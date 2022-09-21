package com.example.mytodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mytodo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: ToDoRepository
): ViewModel() {

    val todolist = repo.getAll().asLiveData()
}