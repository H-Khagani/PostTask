package com.khagani.posttask.adapter

import android.graphics.Color
import android.location.GnssAntennaInfo
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khagani.posttask.R
import com.khagani.posttask.model.PostsModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter (private val postsList: ArrayList<PostsModel>,private val listener : Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> () {

    interface  Listener{
        fun onItemClick(postsModel: PostsModel)
    }

   // private var colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(postsModel: PostsModel, /*colors:ArrayList<String> , position: Int,*/listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(postsModel)
            }
            //itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_userId.text=postsModel.userId.toString()
            itemView.text_id.text=postsModel.id.toString()
            itemView.text_title.text=postsModel.title
            itemView.text_body.text=postsModel.body
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(postsList[position],listener)
    }

    override fun getItemCount(): Int {
        return postsList.count()
    }
}
