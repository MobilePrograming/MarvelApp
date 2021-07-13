package com.guessaname.marvelapp.ui.fragmentdetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
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

        val context = view.context // get actual context
        var characterId = character.characterid

        val fileName = "bookmarks.txt"  // bookmarks list is store in this file
        val path = context.getExternalFilesDir(null) // return file path in internal storage

        val folder = File(path, "bookmarks") // initialize file folder
        folder.mkdirs() // create folder if not yet created

        val file = File(folder, fileName) // initialize file

        val text  = file.readText() // get text from file as string
        val bookmarks_list:MutableList<String> = text.split(",") as MutableList<String> // create bookmarks list from file

        var state = true // item to add

        if(bookmarks_list.contains((characterId).toString())) { // character is alredy in bookmarks list
            btn_bookmarks.setImageDrawable(getDrawable(context ,R.drawable.bookmarkfill))  // change icon to icon fill
            state = false // item to delete
        }

        btn_bookmarks.setOnClickListener { // what to do when bookmark button is press
            if (characterId == null) { // to avoid errors/warnings
                characterId = 0
            }

            if(state) { // add character to bookmark list

                file.appendText(characterId.toString() + ",")  // add character ID to bookmarks list
                state = false  //item to delete

                btn_bookmarks.setImageDrawable(getDrawable(context, R.drawable.bookmarkfill)) // change icon to icon fill
                Toast.makeText(context, "${character.charactername} add to bookmarks!", Toast.LENGTH_SHORT).show()

            } else { // remove character to bookmark list

                val updateText = text.replace(characterId.toString() + "," , "") // remove character ID from bookmarks list
                file.writeText(updateText) // update bookmarks list

                btn_bookmarks.setImageDrawable(getDrawable(context, R.drawable.bookmarks)) // change icon to icon empty
                Toast.makeText(context, "${character.charactername} remove from bookmarks!", Toast.LENGTH_SHORT).show()

            }
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
