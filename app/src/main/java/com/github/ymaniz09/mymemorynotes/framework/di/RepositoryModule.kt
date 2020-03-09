package com.github.ymaniz09.mymemorynotes.framework.di

import android.app.Application
import com.github.ymaniz09.core.repository.NoteRepository
import com.github.ymaniz09.mymemorynotes.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}