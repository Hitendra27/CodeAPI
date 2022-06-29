package com.example.codeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codeapi.databinding.FragmentCodeBinding
import com.example.codeapi.model.ListOfCodes
import com.example.codeapi.model.ListOfCodesItem

class CodeListAdapter (
    private val codeList: MutableList<ListOfCodesItem> = mutableListOf()
        ) : RecyclerView.Adapter<CodesViewHolder>() {

            var onItemClick: ((ListOfCodesItem, Int) -> Unit)? = null

    fun loadData(codeList: MutableList<ListOfCodesItem>) {
        this.codeList.addAll(codeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodesViewHolder {
        return CodesViewHolder(
            FragmentCodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CodesViewHolder, position: Int) {
        holder.setCodeListData(codeList[position], position)
        holder.itemView.rootView.setOnClickListener {
            onItemClick?.invoke(codeList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return codeList.size
    }
        }

    class CodesViewHolder(itemView: FragmentCodeBinding) :
            RecyclerView.ViewHolder(itemView.root) {
                val codeName: TextView = itemView.codeName
                val codeUrl: TextView = itemView.urlName
                val codeSite: TextView = itemView.siteName

        fun setCodeListData(code: ListOfCodesItem, position: Int) {
            codeName.text = "Name: " + code.name
            codeUrl.text = code.url
            codeSite.text = "Site Name: " + code.site
        }
            }

