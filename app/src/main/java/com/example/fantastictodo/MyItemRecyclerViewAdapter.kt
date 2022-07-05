package com.example.fantastictodo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.fantastictodo.databinding.FragmentTodoItemBinding

class MyItemRecyclerViewAdapter(
    private val todoItemList: List<TodoDataModel>,

    ) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    // リスナー格納変数
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentTodoItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoItemList[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
        // タップしたとき
        holder.rootView.setOnClickListener {
            listener.onItemClickListener(it, position, todoItemList[position].id)
        }
    }

    override fun getItemCount(): Int = todoItemList.size

    inner class ViewHolder(binding: FragmentTodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {

            }
        }
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val rootView: View = binding.root
    }


    //インターフェースの作成
    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}