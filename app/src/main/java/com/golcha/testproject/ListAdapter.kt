package com.golcha.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListAdapter(dataModals: List<DataModal>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var dataModals: List<DataModal> = ArrayList()

    init {
        this.dataModals = dataModals
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.custom_list_view, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModal = dataModals[position]
        holder.likes.text = Integer.toString(dataModal.likes)
        holder.comments.text = Integer.toString(dataModal.comments)
        holder.shares.text = Integer.toString(dataModal.shares)
        ImageLoadTask(dataModal.list_image, holder.imageView).execute()
    }

    override fun getItemCount(): Int {
        return dataModals.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var likes: TextView
        var comments: TextView
        var shares: TextView

        init {
            imageView = itemView.findViewById<View>(R.id.img) as ImageView
            likes = itemView.findViewById<View>(R.id.likes) as TextView
            comments = itemView.findViewById<View>(R.id.comments) as TextView
            shares = itemView.findViewById<View>(R.id.shares) as TextView
        }
    }


}