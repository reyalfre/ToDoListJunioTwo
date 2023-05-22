package com.example.todolistjuniotwo

object ListSingleton {
    private val INSTANCE: List<Note> by lazy {
        ArrayList<Note>()
    }

    fun getInstance() = INSTANCE
}