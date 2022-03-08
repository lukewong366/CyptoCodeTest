package com.example.cyptocodetest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cyptocodetest.databinding.FragmentCurrencyListBinding
import com.example.cyptocodetest.viewmodel.DemoActivityViewModel


class CurrencyListFragment : Fragment() {
    private val viewModel: DemoActivityViewModel by activityViewModels()
    private var binding: FragmentCurrencyListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyListBinding.inflate(inflater)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewmodel = viewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerView?.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = viewModel.dataList.value?.let { it ->
                CurrencyListAdapter(it) {
                    Toast.makeText(
                        context,
                        "You click " + it?.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewModel.dataList.observe(
            viewLifecycleOwner
        ) {
            (binding?.recyclerView?.adapter as CurrencyListAdapter).updateList(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return CurrencyListFragment()
        }
    }
}