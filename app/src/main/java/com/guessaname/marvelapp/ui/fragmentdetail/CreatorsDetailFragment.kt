package com.guessaname.marvelapp.ui.fragmentdetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.guessaname.marvelapp.MainActivity
import com.guessaname.marvelapp.R
import com.guessaname.marvelapp.data.model.Creator
import com.guessaname.marvelapp.databinding.FragmentCreatorsDetailBinding
import com.guessaname.marvelapp.ui.viewmodel.CreatorsViewModel
import com.guessaname.marvelapp.utils.autoCleared
import java.io.File


class CreatorsDetailFragment : Fragment() {

    private var binding: FragmentCreatorsDetailBinding by autoCleared()
    private lateinit var viewModel: CreatorsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentCreatorsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).creatorsViewModel
        val creator = arguments?.getSerializable("creator")
        setup(creator as Creator)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = creator.name

        val context = view.context // get actual context
        val creatorId = creator.id

        val fileNameId = "characterid.txt"

        val path = context.getExternalFilesDir(null) // return file path in internal storage

        val folder = File(path, "bookmarks") // initialize file folder
        folder.mkdirs() // create folder if not yet created

        val fileid = File(folder, fileNameId)

        fileid.createNewFile()
        fileid.writeText(creatorId.toString())

    }

    private fun setup(creator: Creator) {
        binding.creatorsDetailName.text = creator.name
        activity?.let {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_foreground)
            requestOptions.error(R.drawable.ic_launcher_foreground)
            Glide.with(this)
                .load("${creator.thumbnail?.path}.${creator.thumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.creatorDetailImage)
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