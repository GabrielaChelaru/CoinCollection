package com.cna.coincollection

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(coinList: MutableList<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    var context: Context? = null
    private var coin: MutableList<String> = coinList /// O sa folosim vectorul pentru a popula recyclerview-ul cu datele din acesta


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    } /// Face inflate la interfata pentru item

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = coin[position]
    }

    override fun getItemCount(): Int {
        return coin.size
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
         var itemImage: ImageView
         var itemTitle: TextView

        init{
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle) // Conectam la textView-ul din interfata cu id-ul itemTitle

            itemView.setOnClickListener {

                val position: Int = adapterPosition // salvam pozitia item-ului curent in position
                val shareIntent = Intent() // facem un nou intent
                shareIntent.action = Intent.ACTION_SEND // Spunem ca actiunea este de tipul send
                shareIntent.type="text/plain" // Spunem ca tipul de continut de este text
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I just collected ${coin[position]}") /// Punem informatia in intent
                context?.startActivity(Intent.createChooser(shareIntent,"Share my coin")) /// Lansam intent-ul

            }

        }
    }


}