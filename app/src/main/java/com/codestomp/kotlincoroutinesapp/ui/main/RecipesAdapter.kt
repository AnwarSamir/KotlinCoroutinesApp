package com.codestomp.kotlincoroutinesapp.ui.main

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codestomp.kotlincoroutinesapp.R
import com.codestomp.kotlincoroutinesapp.data.models.Data
import com.codestomp.kotlincoroutinesapp.databinding.RowRecipesBinding


class RecipesAdapter(private val data: ArrayList<Data>,
                     onItemCLicked: OnItemCLicked) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemCLicked=onItemCLicked



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RowRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val itemViewHolder = holder as ItemViewHolder
        val binding= itemViewHolder.viewBinding

        Glide.with(holder.itemView)  //2
            .load("https://codestomp.com/CodeStompApps/test/"+data[position].image) //3
            .centerCrop() //4
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into( binding.image) //8


        // handle recipe  desc
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvDesc.text =
                Html.fromHtml(data[position].name, Html.FROM_HTML_MODE_COMPACT);
        } else {

            binding.tvDesc.text= Html.fromHtml(data[position].name);
        }




        // handle recipe type
        when (data[position].type) {
            "B" -> binding.tvType.text="وصفات الفطار"
            "L" -> binding.tvType.text="وصفات الغداء"
            "D" -> binding.tvType.text="وصفات العشاء"
            else -> binding.tvType.text="كل الوصفات"
        }


        holder.itemView.setOnClickListener {
            onItemCLicked.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(var viewBinding: RowRecipesBinding) : RecyclerView.ViewHolder(viewBinding.root)


    interface  OnItemCLicked {
        fun  onClick(pos:Int)
    }


}