package com.example.notesapp.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.Models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (note: Note, onConflictStrategy: Any)
     @Delete
    suspend fun delete (note:Note)

     @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
    @Query( "UPDATE notes_table Set title = :title , note =:note WHERE id = :id"  )
    suspend fun update (id :Int?,title : String?,note : String?)
    abstract fun insert(note: Note)
}