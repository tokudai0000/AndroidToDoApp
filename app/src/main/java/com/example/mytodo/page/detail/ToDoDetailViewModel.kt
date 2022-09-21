package com.example.mytodo.page.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.model.todo.ToDo
import com.example.mytodo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoDetailViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val todo = savedStateHandle.getLiveData<ToDo>("todo")
    val errorMessage = MutableLiveData<String>()
    val deleted = MutableLiveData<Boolean>()

    fun delete() {
        viewModelScope.launch {
            try {
                val todo = this@ToDoDetailViewModel.todo.value ?: return@launch
                toDoRepository.delete(todo)
                deleted.value = true
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

}