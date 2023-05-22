package com.example.todolistjuniotwo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistjuniotwo.databinding.ItemNoteBinding

class NoteAdapter(private var noteList: MutableList<Note>, private var listener: NoteOnClickListener): RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
   // private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemNoteBinding.bind(view)

        fun bind(note: Note){
            binding.textViewTitle.text = note.title
            binding.textViewSubtitulo.text = note.descripcion
            binding.checkBox.isChecked = note.tick
        }
        fun onListener(note: Note){
            binding.imageViewEdit.setOnClickListener { listener.onClickEdit(note) }
            binding.imageViewDelete.setOnClickListener { listener.onClickDelete(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return noteList.size
    }
    fun add(note: Note){
        if (!noteList.contains(note)){
            noteList.add(note)
        notifyItemInserted(noteList.size-1)
        }
    }
    fun update(note: Note){

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = noteList.get(position)
        holder.bind(item)
        holder.onListener(item)
    }
}