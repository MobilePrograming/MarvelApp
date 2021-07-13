package com.guessaname.marvelapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.ui.adapter.ComicsAdapter
import com.guessaname.marvelapp.ui.viewmodel.ComicsViewModel
import com.guessaname.marvelapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_comic.*
import com.guessaname.marvelapp.databinding.FragmentComicBinding
import kotlinx.android.synthetic.main.fragment_character_detail.*


class ComicsFragment : Fragment() {

    private lateinit var viewModel: ComicsViewModel
    private lateinit var comicsAdapter: ComicsAdapter
    private var _binding: FragmentComicBinding? = null
    private val binding get() = _binding!!

    val TAG = "ComicTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic, frame_comics, true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).comicsViewModel
        recyclerView()

        comicsAdapter.setOnItemClicklistener {
            val bundle = Bundle().apply {
                putSerializable("character",it)
            }
            /*
            findNavController().navigate(
                R.id.action_comicFragment_to_comicDetailFragment,
                bundle
            )
            */
        }

        viewModel.comics.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {  characterResponse ->
                        comicsAdapter.differ.submitList(characterResponse.comicData?.results)
                        comicsAdapter.notifyDataSetChanged()
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
        comicprogressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        comicprogressBar.visibility = View.GONE
    }

    private fun recyclerView(){
        comicsAdapter = ComicsAdapter()
        rvComic.apply {
            adapter = comicsAdapter
            comicsAdapter.notifyDataSetChanged()
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}