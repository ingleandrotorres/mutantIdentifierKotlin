package com.example.mutantidentifier.framework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mutantidentifier.R
import com.example.mutantidentifier.data.models.Adn

class MutantListAdapter(private val data : List<Adn>) : RecyclerView.Adapter<MutantListAdapter.ViewHolderMutantList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMutantList {
        return ViewHolderMutantList(LayoutInflater.from(parent.context).inflate(R.layout.view_mutant_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderMutantList, position: Int) {
        val adn = data[position]
        holder.bind(adn)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolderMutantList(view: View):RecyclerView.ViewHolder(view) {

        private val adnTextView = view.findViewById<TextView>(R.id.textView_adn)
        private val image = view.findViewById<ImageView>(R.id.imageView_adn_result)

        fun bind(adn : Adn) {
            adnTextView.text = adn.value
            if (adn.isMutant == true)image.setImageResource(R.drawable.deadpool2)
            else image.setImageResource(R.drawable.human)
        }
    }

}