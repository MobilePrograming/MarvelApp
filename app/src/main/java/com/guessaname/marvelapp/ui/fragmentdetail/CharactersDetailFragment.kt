package com.guessaname.marvelapp.ui.fragmentdetail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.data.model.Character
import com.guessaname.marvelapp.databinding.FragmentCharacterDetailBinding
import com.guessaname.marvelapp.ui.adapter.ComicsAdapter
import com.guessaname.marvelapp.ui.viewmodel.CharactersViewModel
import com.guessaname.marvelapp.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_character_detail.*


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
        setHasOptionsMenu(true)
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).charactersViewModel

        val character = arguments?.getSerializable("character")
        setup(character as Character)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = character.charactername

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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.add("Menu item")
            .setIcon(R.drawable.bookmark_selector)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
    }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when (item.itemId) {
           android.R.id.home -> {
               activity?.onBackPressed()
               return true
           }


       }
       return super.onOptionsItemSelected(item)
   }
}