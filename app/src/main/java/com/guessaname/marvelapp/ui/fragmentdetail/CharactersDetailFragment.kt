package com.guessaname.marvelapp.ui.fragmentdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.bookmarksDB.Bookmark
import com.guessaname.marvelapp.bookmarksDB.BookmarkDB
import com.guessaname.marvelapp.data.model.Character
import com.guessaname.marvelapp.databinding.FragmentCharacterDetailBinding
import com.guessaname.marvelapp.ui.viewmodel.CharactersViewModel
import com.guessaname.marvelapp.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_character_detail.*
import java.time.chrono.JapaneseEra.values

class CharactersDetailFragment : Fragment() {

    private var binding: FragmentCharacterDetailBinding by autoCleared()
    private lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).charactersViewModel
        val character = arguments?.getSerializable("character")
        setup(character as Character)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = character.charactername

        val context = view.context
        val btn_bookmark = btn_bookmarks

        // TODO: make icon fill when button is pressed
        //btn_bookmark.setImageIcon(ContextCompat.getDrawable(context, R.drawable.bookmarks))

        val db= Room.databaseBuilder(context ,BookmarkDB::class.java,"bookmarks_list").build()

        // TODO: make thread for run DB insert to avoid error
        //  (Cannot access database on the main thread since it may potentially lock the UI for a long period of time.)

        btn_bookmark.setOnClickListener{
            // TODO: make icon fill when button is pressed
            //btn_bookmark.setBackgroundResource(R.drawable.bookmarkfill)
            val bookmark = Bookmark(1, 1)
            //db.BookmarksDao.insert(bookmark)  // uncomment

            Toast.makeText(context, "You clicked me.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setup(character: Character) {
        binding.characterDetailExplanation.text = character.characterdescription
        activity?.let {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)
            Glide.with(this)
                .load("${character.characterthumbnail?.path}.${character.characterthumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.characterDetailImage)
        }
    }

}
