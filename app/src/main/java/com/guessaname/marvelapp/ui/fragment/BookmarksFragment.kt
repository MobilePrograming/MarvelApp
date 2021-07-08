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
import com.guessaname.marvelapp.databinding.FragmentBookMarkBinding
import com.guessaname.marvelapp.ui.adapter.BookmarksAdapter
import kotlinx.android.synthetic.main.fragment_book_mark.*


class BookmarksFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var bookmarksAdapter: BookmarksAdapter
    private var _binding: FragmentBookMarkBinding? = null
    private val binding get() = _binding!!

    val TAG = "BookmarkTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_mark, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).charactersViewModel
        recyclerView()

        bookmarksAdapter.setOnItemClicklistener {
            val bundle = Bundle().apply {
                putSerializable("character",it)
            }
            findNavController().navigate(
                R.id.action_bookmarksFragment_to_characterDetailFragment,
                bundle
            )
        }

        viewModel.characters.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {  characterResponse ->
                        bookmarksAdapter.differ.submitList(characterResponse.characterData?.results)
                        bookmarksAdapter.notifyDataSetChanged()
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
        bookmarkprogressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        bookmarkprogressBar.visibility = View.GONE
    }

    private fun recyclerView(){
        bookmarksAdapter = BookmarksAdapter()
        rvBookmark.apply {
            adapter = bookmarksAdapter
            bookmarksAdapter.notifyDataSetChanged()
            layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL,false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}