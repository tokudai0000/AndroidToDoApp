package com.example.mytodo.model.todo

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ToDo (
    @PrimaryKey(autoGenerate = true)    // _idを主キーにする
    val _id:Int = 0,
    val title: String,
    val detail: String,
    val created: Long,
    val modified: Long
    ): Parcelable