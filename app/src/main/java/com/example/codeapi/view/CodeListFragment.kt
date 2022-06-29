package com.example.codeapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codeapi.adapter.CodeListAdapter
import com.example.codeapi.databinding.CodellistSiteLayoutBinding
import com.example.codeapi.databinding.FragmentCodeBinding
import com.example.codeapi.model.ListOfCodes
import com.example.codeapi.utils.UIState
import com.example.codeapi.viewmodel.CodeListViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class CodeListFragment : Fragment() {

    private val binding by lazy {
        CodellistSiteLayoutBinding.inflate(layoutInflater)
    }

    private val codeListViewModel : CodeListViewModel by viewModel()
    private lateinit var codeListAdapter: CodeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        codeListAdapter = CodeListAdapter()
        binding.codeList.layoutManager = LinearLayoutManager(context)
        binding.codeList.adapter = codeListAdapter
        codeListViewModel.codeListLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS -> {
                    val codes:ListOfCodes = response.success as ListOfCodes
                    codeListAdapter.loadData(codes)
                }
                is UIState.ERROR -> {
                    Toast.makeText(context, response.error.message,
                        Toast.LENGTH_LONG).show()
                }
            }
        })
        codeListViewModel.subcribleToCodeListInfo()
        return binding.root
    }
}