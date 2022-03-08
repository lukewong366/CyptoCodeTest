package com.example.cyptocodetest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cyptocodetest.R
import com.example.cyptocodetest.databinding.ItemListBinding
import com.example.cyptocodetest.model.CurrencyInfo

class CurrencyListAdapter(private var currencyInfoList: ArrayList<CurrencyInfo> = ArrayList(),
                          private val onSelect: (CurrencyInfo?) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater
            .from(parent.context)
        val binding : ItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list, parent, false)
        return CurrencyListHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CurrencyListHolder) {
            holder.bind(currencyInfoList[position], onSelect)
        }
    }

   class CurrencyListHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
       private val binding : ItemListBinding? = DataBindingUtil.bind(itemView)
       fun bind(currencyInfo: CurrencyInfo?, onSelect: (CurrencyInfo?) -> Unit) {
           // bind your view here
           binding?.textName?.text = currencyInfo?.name
           binding?.textCurrency?.text = currencyInfo?.id
           binding?.root?.setOnClickListener {
               onSelect(currencyInfo)
           }
       }
    }

    fun updateList(currencyInfoList: ArrayList<CurrencyInfo>) {
        this.currencyInfoList = currencyInfoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return currencyInfoList.size
    }
}
