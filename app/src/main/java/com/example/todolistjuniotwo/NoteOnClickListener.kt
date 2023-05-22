package com.example.todolistjuniotwo

interface NoteOnClickListener {

    fun onClickEdit(note: Note)
    fun onClickDelete(note: Note)
}