package com.guessaname.marvelapp.ui.fragmentdetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.data.model.Character
import com.guessaname.marvelapp.databinding.FragmentCharacterDetailBinding
import com.guessaname.marvelapp.ui.viewmodel.CharactersViewModel
import com.guessaname.marvelapp.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_character_detail.*
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.text.Charsets.UTF_8

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

        val fileName = "bookmarks.txt"
        val path = context.getExternalFilesDir(null)

        val folder = File(path, "bookmarks")
        folder.mkdirs()

        val file = File(folder, fileName)

        btn_bookmarks.setOnClickListener {
            var characterId = character.characterid
            if (characterId == null) {
                characterId = 0
            }

                file.appendText(characterId.toString() + ",")
                Toast.makeText(context, "${character.charactername} added to bookmarks!", Toast.LENGTH_SHORT).show()
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
