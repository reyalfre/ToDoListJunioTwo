package com.example.todolistjuniotwo

data class Note(
    var id: Int? = null,
    var title: String,
    var subTitle: String,
    var descripcion: String,
    var category: String,
    var tick: Boolean = false
)