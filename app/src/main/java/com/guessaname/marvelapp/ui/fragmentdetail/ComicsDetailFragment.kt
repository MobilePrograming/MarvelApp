package com.guessaname.marvelapp.ui.fragmentdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.data.model.Comic
import com.guessaname.marvelapp.databinding.FragmentComicDetailBinding
import com.guessaname.marvelapp.ui.viewmodel.ComicsViewModel
import com.guessaname.marvelapp.utils.autoCleared


class ComicsDetailFragment : Fragment() {

    private var binding: FragmentComicDetailBinding by autoCleared()
    private lateinit var viewModel: ComicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentComicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).comicsViewModel
        val comic = arguments?.getSerializable("comic")
        setup(comic as Comic)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = comic.comictitle

    }

    private fun setup(comic: Comic) {
        binding.comicDetailExplanation.text = comic.comictitle
        //binding.comicsRecyclerview
        activity?.let {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)
            Glide.with(this)
                .load("${comic.comicthumbnail?.path}.${comic.comicthumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.comicDetailImage)
        }
    }
}