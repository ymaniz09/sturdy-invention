package com.github.ymaniz09.core.usecase

import com.github.ymaniz09.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAll()
}