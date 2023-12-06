package com.pratyushvkp.notes.noteSegment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.pratyushvkp.notes.R
import com.pratyushvkp.notes.Utils.Constants
import com.pratyushvkp.notes.Utils.Constants.PREFS_TOKEN_FILE
import com.pratyushvkp.notes.Utils.NetworkResult
import com.pratyushvkp.notes.databinding.FragmentMainBinding
import com.pratyushvkp.notes.models.NoteResponse
import com.pratyushvkp.notes.userInterface.login.loginFragment
import com.pratyushvkp.notes.userInterface.login.registerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class mainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()

    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
       adapter = NoteAdapter(::onNoteClicked)
        return binding.root
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        noteViewModel.getNotes()
        binding.noteList.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.noteList.adapter = adapter
        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_noteFragment)
        }
        binding.logOutbtn.setOnClickListener {
            clearUserSessionData()

           findNavController().navigate(R.id.action_mainFragment_to_registerFragment)

        }
    }

    private fun clearUserSessionData() {
        // You are using SharedPreferences to store user data

        val sharedPreferences = requireContext().getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Clear user-specific data
        editor.clear()

        // Commit the changes to clear the data
        editor.apply()
    }

    private fun bindObservers() {
        noteViewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
           binding.progressBar.isVisible = false
            when(it){
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    private fun onNoteClicked(noteResponse: NoteResponse){
        val bundle = Bundle()
        bundle.putString("note", Gson().toJson(noteResponse))
        findNavController().navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}