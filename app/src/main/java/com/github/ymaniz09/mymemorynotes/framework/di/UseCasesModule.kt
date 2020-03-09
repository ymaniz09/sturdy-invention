package com.github.ymaniz09.mymemorynotes.framework.di

import com.github.ymaniz09.core.repository.NoteRepository
import com.github.ymaniz09.core.usecase.*
import com.github.ymaniz09.mymemorynotes.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}