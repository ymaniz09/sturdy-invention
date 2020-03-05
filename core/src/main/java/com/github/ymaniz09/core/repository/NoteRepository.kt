package com.github.ymaniz09.core.repository

import com.github.ymaniz09.core.data.Note

class NoteRepository(private val dataSource: NoteDateSource) {

    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getAll() = dataSource.getAll()

    suspend fun removeNote(note: Note) = dataSource.remove(note)
}