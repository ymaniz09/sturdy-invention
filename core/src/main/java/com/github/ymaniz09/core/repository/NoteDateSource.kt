package com.github.ymaniz09.core.repository

import com.github.ymaniz09.core.data.Note

interface NoteDateSource {
    suspend fun add(note: Note)

    suspend fun get(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note)
}