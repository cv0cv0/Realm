package me.gr.realm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import me.gr.realm.R
import me.gr.realm.entity.User

/**
 * Created by GR on 2017/9/11.
 */
class MainAdapter(data: OrderedRealmCollection<User>?) : RealmRecyclerViewAdapter<User, MainAdapter.ViewHolder>(data, true) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val user = getItem(position)
        holder?.nameText?.text = user?.name
        holder?.ageText?.text = user?.age.toString()
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView?.findViewById<TextView>(R.id.name_text)
        val ageText = itemView?.findViewById<TextView>(R.id.age_text)
    }
}