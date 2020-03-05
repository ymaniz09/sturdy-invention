package com.github.ymaniz09.mymemorynotes.framework

import com.github.ymaniz09.core.usecase.AddNote
import com.github.ymaniz09.core.usecase.GetAllNotes
import com.github.ymaniz09.core.usecase.GetNote
import com.github.ymaniz09.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)