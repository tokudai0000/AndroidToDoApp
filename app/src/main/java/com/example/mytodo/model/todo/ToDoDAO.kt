package com.example.mytodo.model.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {

//    データの取得（作成日時が指定したもの未満で、上位n件を取ってくる）
    @Query("select * from ToDo where created < :startCreated order by created desc limit :limit")
    fun getWithCreated(startCreated: Long, limit: Int): Flow<List<ToDo>>

    @Query("select * from ToDo order by created desc")
    fun getAll(): Flow<List<ToDo>>

//    これらの詳しい処理内容はリポジトリで定義
    @Insert
    suspend fun create(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Delete
    suspend fun delete(todo: ToDo)
}