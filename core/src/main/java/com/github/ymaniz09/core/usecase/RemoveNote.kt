package com.github.ymaniz09.core.usecase

import com.github.ymaniz09.core.data.Note
import com.github.ymaniz09.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}