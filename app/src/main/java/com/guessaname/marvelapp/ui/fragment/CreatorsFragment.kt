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
import com.guessaname.marvelapp.ui.adapter.CreatorsAdapter
import com.guessaname.marvelapp.ui.viewmodel.CreatorsViewModel
import com.guessaname.marvelapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_creators.*
import com.guessaname.marvelapp.databinding.FragmentCreatorsBinding
import java.io.File

class CreatorsFragment : Fragment() {

    private lateinit var viewModel: CreatorsViewModel
    private lateinit var creatorsAdapter: CreatorsAdapter
    private var _binding: FragmentCreatorsBinding? = null
    private val binding get() = _binding!!

    val TAG = "CreatorsTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creators, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).creatorsViewModel

        recyclerView()

        creatorsAdapter.setOnItemClicklistener {
            val bundle = Bundle().apply {
                putSerializable("creator",it)
            }
            findNavController().navigate(
                R.id.action_creatorsFragment_to_creatorsDetailFragment,
                bundle
            )
        }

        viewModel.creators.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {  creatorResponse ->
                        creatorsAdapter.differ.submitList(creatorResponse.creatorData?.results)
                        creatorsAdapter.notifyDataSetChanged()
                        Log.i(TAG, "Error is: $creatorResponse")

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
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun recyclerView(){
        creatorsAdapter = CreatorsAdapter()
        rvCreators.apply {
            adapter = creatorsAdapter
            creatorsAdapter.notifyDataSetChanged()
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL,false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}