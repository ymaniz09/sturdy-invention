package com.github.ymaniz09.core.usecase

import com.github.ymaniz09.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}