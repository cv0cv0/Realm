package me.gr.realm.util

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View

/**
 * Created by GR on 2017/9/12.
 */
class SwipeRemoveCallback : ItemTouchHelper.Callback() {
    private var onItemRemove: ((view: View, position: Int) -> Unit)? = null

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemRemove?.invoke(viewHolder.itemView, viewHolder.adapterPosition)
    }

    fun setOnItemRemoveListener(listener:((view: View, position: Int) -> Unit)){
        onItemRemove=listener
    }
}