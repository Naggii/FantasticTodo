package com.example.fantastictodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * A fragment representing a list of Items.
 */
class TodoItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                val todoList: MutableList<TodoDataModel> = arrayListOf()

                if (IS_DEBUG) {
                    for (i in 1..99) {
                        todoList.add(
                            TodoDataModel(
                                id = i.toString(),
                                content = "ああああああああああああああああああああああああああ",
                                details = i.toString()
                            )
                        )
                    }
                }
                val adapter = MyItemRecyclerViewAdapter(todoList)
                this.adapter = adapter
                adapter.setOnItemClickListener(object:MyItemRecyclerViewAdapter.OnItemClickListener{
                    override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                        Toast.makeText(requireContext(), "${clickedText}がタップされました", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val IS_DEBUG = true
    }
}