package com.ibrahim.demo.cardanim.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.demo.cardanim.model.Person
import com.ibrahim.demo.cardanim.databinding.RvItemBinding
import kotlinx.android.synthetic.main.rv_item.view.*

class PersonRecyclerviewAdapter (var items: ArrayList<Person> )
    : RecyclerView.Adapter<PersonRecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position), itemClickListener)
    fun replaceData(it: List<Person>) {
        items = it as ArrayList<Person>
        notifyDataSetChanged()
    }
    fun removeData(position : Int){
        if (items.size > position) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            repo: Person,
            itemClickListener: OnItemClickListener?
        ) {
            /*with(binding){
                rvItemName.text = repo.gender
            }*/
            binding.personViewModel = repo
            if (itemClickListener != null) {
                binding.root.rv_item_connect.setOnClickListener({ _ -> itemClickListener.onItemClick(layoutPosition) })
            }

            binding.executePendingBindings()
        }
    }
}