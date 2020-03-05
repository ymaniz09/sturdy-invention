package com.github.ymaniz09.core.usecase

import com.github.ymaniz09.core.data.Note
import com.github.ymaniz09.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}