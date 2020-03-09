package com.github.ymaniz09.mymemorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.github.ymaniz09.core.data.Note
import com.github.ymaniz09.mymemorynotes.R
import com.github.ymaniz09.mymemorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId

            if (noteId != 0L) {
                viewModel.getNote(noteId)
            }
        }

        saveNoteFloatingActionButton.setOnClickListener {

            if (!TextUtils.isEmpty(titleEditText.text.toString()) || !TextUtils.isEmpty(
                    contentEditText.text.toString()
                )
            ) {
                val time = System.currentTimeMillis()
                currentNote.title = titleEditText.text.toString()
                currentNote.content = contentEditText.text.toString()
                currentNote.updateTime = time

                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }

                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(titleEditText).popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Something when wrong, please try again!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.currentNote.observe(this, Observer { note ->
            note?.let {
                currentNote = it
                titleEditText.setText(it.title, TextView.BufferType.EDITABLE)
                contentEditText.setText(it.title, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleEditText.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNote -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(context!!)
                        .setTitle(getString(R.string.delete_note_title))
                        .setMessage(getString(R.string.dete_note_message))
                        .setPositiveButton(getString(R.string.delete_note_positive_button)) { _: DialogInterface?, _: Int ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton(getString(R.string.delete_note_negative_button)) { _: DialogInterface?, _: Int -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }
}
