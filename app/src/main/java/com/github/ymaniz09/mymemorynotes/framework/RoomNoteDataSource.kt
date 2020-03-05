package com.github.ymaniz09.mymemorynotes.framework

import android.content.Context
import com.github.ymaniz09.core.data.Note
import com.github.ymaniz09.core.repository.NoteDateSource
import com.github.ymaniz09.mymemorynotes.framework.db.DatabaseService
import com.github.ymaniz09.mymemorynotes.framework.db.NoteEntity

class RoomNoteDataSource(context: Context) : NoteDateSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}