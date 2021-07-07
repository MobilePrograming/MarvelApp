package com.guessaname.marvelapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.ui.adapter.CharactersAdapter
import com.guessaname.marvelapp.ui.viewmodel.CharactersViewModel
import com.guessaname.marvelapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_character.*
import com.guessaname.marvelapp.databinding.FragmentCharacterBinding


class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var charactersAdapter: CharactersAdapter
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    val TAG = "CharacterTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).charactersViewModel
        recyclerView()

        charactersAdapter.setOnItemClicklistener {
            val bundle = Bundle().apply {
                putSerializable("character",it)
            }
            findNavController().navigate(
                R.id.action_characterFragment_to_characterDetailFragment,
                bundle
            )
        }

        viewModel.characters.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {  characterResponse ->
                        charactersAdapter.differ.submitList(characterResponse.characterData?.results)
                        charactersAdapter.notifyDataSetChanged()
                        Log.i(TAG, "Error is: $characterResponse")

                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error is: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }


    private fun showProgressBar() {
        characterprogressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        characterprogressBar.visibility = View.GONE
    }

    private fun recyclerView(){
        charactersAdapter = CharactersAdapter()
        rvCharacter.apply {
            adapter = charactersAdapter
            charactersAdapter.notifyDataSetChanged()
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL,false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}