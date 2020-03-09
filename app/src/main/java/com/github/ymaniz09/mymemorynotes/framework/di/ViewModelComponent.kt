package com.github.ymaniz09.mymemorynotes.framework.di

import com.github.ymaniz09.mymemorynotes.framework.ListViewModel
import com.github.ymaniz09.mymemorynotes.framework.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}