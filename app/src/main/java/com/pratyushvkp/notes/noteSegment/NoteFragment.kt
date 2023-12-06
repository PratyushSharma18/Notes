package com.pratyushvkp.notes.userInterfaceUI.login.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.pratyushvkp.notes.Utils.NetworkResult
import com.pratyushvkp.notes.databinding.FragmentNoteBinding
import com.pratyushvkp.notes.models.NoteRequest
import com.pratyushvkp.notes.models.NoteResponse
import com.pratyushvkp.notes.noteSegment.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class noteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()
    private var note: NoteResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        bindHandlers()
        bindObservers()
    }



    private fun bindHandlers() {
        binding.btnDelete.setOnClickListener {
            note?.let { noteViewModel.deleteNote(it!!._id) }
        }

        binding.btnSubmit.setOnClickListener {
            val title = binding.txtTitle.text.toString()
            val description = binding.txtDescription.text.toString()
            val noteRequest = NoteRequest(description, title)
            if(note == null){
                noteViewModel.createNote(noteRequest)
            } else{
                noteViewModel.updateNote(note!!._id,noteRequest)
            }
        }
    }

    private fun bindObservers() {
   noteViewModel.statusLiveData.observe(viewLifecycleOwner, Observer{
    when (it){
        is NetworkResult.Success -> {
            findNavController().popBackStack()
        }
        is NetworkResult.Error -> {

        }
        is NetworkResult.Loading -> {

        }
    }
})
    }

    private fun setInitialData() {
        val jsonNote = arguments?.getString("note")
        if(jsonNote != null){
          note = Gson().fromJson(jsonNote, NoteResponse::class.java)
            note?.let {
                binding.txtTitle.setText(it.title)
                binding.txtDescription.setText(it.description)
            }
        } else{
            binding.addEditText.text = "Add Note"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}