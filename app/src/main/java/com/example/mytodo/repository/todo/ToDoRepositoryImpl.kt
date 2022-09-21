package com.example.mytodo.repository.todo

import com.example.mytodo.model.todo.ToDo
import com.example.mytodo.model.todo.ToDoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val dao: ToDoDAO
    ): ToDoRepository {
    override fun getAll(): Flow<List<ToDo>> {
        return dao.getAll()
    }

    override suspend fun create(title: String, detail: String) {
        val now = System.currentTimeMillis()
        val todo = ToDo(title = title, detail = detail, created = now, modified = now)
        withContext(Dispatchers.IO){
            dao.create(todo)
        }
    }

    override suspend fun update(todo: ToDo, title: String, detail: String): ToDo {
        val updateToDo = ToDo (
            _id = todo._id,
            title = title,
            detail = detail,
            created = todo.created,
            modified = System.currentTimeMillis()
        )
        withContext(Dispatchers.IO) {
            dao.update(updateToDo)
        }
        return updateToDo
    }

    override suspend fun delete(todo: ToDo) {
        dao.delete(todo)
    }
}