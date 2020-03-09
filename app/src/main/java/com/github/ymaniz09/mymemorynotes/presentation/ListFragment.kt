package com.github.ymaniz09.mymemorynotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.github.ymaniz09.mymemorynotes.R
import com.github.ymaniz09.mymemorynotes.framework.ListViewModel
import com.github.ymaniz09.mymemorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), ListAction {

    private val notesListAdapter = NotesListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        addNoteFloatingActionButton.setOnClickListener { gotToNoteDetails() }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(this, Observer {notesList ->
            loadingProgressBar.visibility = View.GONE
            notesRecyclerView.visibility = View.VISIBLE

            notesListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getNotes()
    }

    private fun gotToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(notesRecyclerView).navigate(action)
    }

    override fun onClick(id: Long) {
        gotToNoteDetails(id)
    }

}
