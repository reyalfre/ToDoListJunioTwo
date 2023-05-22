package com.example.todolistjuniotwo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistjuniotwo.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), NoteOnClickListener {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var mAdapter: NoteAdapter
    private lateinit var mLayout: LinearLayoutManager

    private lateinit var listNote: ArrayList<Note>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listNote = ArrayList()
        listNote = ListSingleton.getInstance() as ArrayList<Note>

        setupRecyclerView()
        setupButtom()
    }

    private fun setupButtom() {
        binding.floatingActionButton2.setOnClickListener { showInputDialog() }

    }

    private fun showInputDialog() {
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.view_dialog, null)

        val title = customView.findViewById<EditText>(R.id.editTextTitle)
        val subtitle = customView.findViewById<EditText>(R.id.editTextText2)
        val descripcion = customView.findViewById<EditText>(R.id.editTextDesc)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(customView)
        builder.setTitle("Add note")

        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialogInterface, i ->
            val titleMia = title.text.toString()
            val subtitleMia = subtitle.text.toString()
            val descripcionMia = descripcion.text.toString()
            //La de abajo es la manera para hacerlo en una sola linea
            // val subtitle = customView.findViewById<EditText>(R.id.editTextText2).text.toString()
            listNote.add(
                Note(
                    id = null,
                    title = titleMia,
                    subTitle = subtitleMia,
                    descripcion = descripcionMia,
                    category = "",
                    tick = true
                )
            )
            mAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "añadido", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()

        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(requireContext(), "Cancelar", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        })
        val dialogo = builder.create()
        dialogo.show()
    }

    private fun setupRecyclerView() {
      //  mAdapter = NoteAdapter(mutableListOf())
        mAdapter = NoteAdapter(listNote, this)
        mLayout = LinearLayoutManager(requireContext())

        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = mAdapter
        }
        /*(1..10).forEach {
            val note = Note(it, "Titulo$it", "Subtitulo $it", "", "Una", false)
            mAdapter.add(note)
        }*/

    }

    override fun onClickEdit(note: Note) {
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.view_dialog, null)

        val title = customView.findViewById<EditText>(R.id.editTextTitle)
        val subTitt = customView.findViewById<EditText>(R.id.editTextText2)
        val descrp = customView.findViewById<EditText>(R.id.editTextDesc)

        title.setText(note.title)
        subTitt.setText(note.subTitle)
        descrp.setText(note.descripcion)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(customView)
        builder.setTitle("Add note")

        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialogInterface, i ->
            val titleMia = title.text.toString()
            val subtitleMia = subTitt.text.toString()
            val descripcionMia = descrp.text.toString()
            //La de abajo es la manera para hacerlo en una sola linea
            // val subtitle = customView.findViewById<EditText>(R.id.editTextText2).text.toString()
            mAdapter.add(note)
            mAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "añadido", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()

        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(requireContext(), "Cancelar", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        })
        val dialogo = builder.create()
        dialogo.show()

    }
    override fun onClickDelete(note: Note) {
    val builder = AlertDialog.Builder(requireContext())
        .setTitle("Delete")
        .setIcon(R.drawable.ic_delete)
        .setMessage("Atencion vas a borrar")
        .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
            listNote.remove(note)
            mAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(),"Borrar", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        })
        val dialogo = builder.create()
        dialogo.show()
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val showDialogButton = findViewById<Button>(R.id.floatingActionButton2)
        showDialogButton
        configButtons()
    }

    private fun configButtons() {
       // binding.floatingActionButton2.

    }*/
}