package com.github.ymaniz09.mymemorynotes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ymaniz09.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    var title: String,
    var content: String,
    @ColumnInfo(name = "creation_date")
    var creationTime: Long,
    @ColumnInfo(name = "update_date")
    var updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(note.title, note.content, note.creationTime, note.updateTime, note.id)
    }

    fun toNote() = Note(title, content, creationTime, updateTime, id)
}