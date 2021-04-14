package com.example.myappforjob.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myappforjob.view.PhotoDetail
import com.example.myappforjob.R
import com.example.myappforjob.model.PhotoModel

class PhotoAdapter() : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    var photoList: ArrayList<PhotoModel> = ArrayList()
    var context:Context? = null
    constructor(context: Context) : this() {
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent , false)
        return  PhotoViewHolder(view)
    }
    fun setArrayList(list: ArrayList<PhotoModel>) {
        photoList?.clear()
        photoList?.addAll(list)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        var photoModel: PhotoModel = photoList!![position]
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent: Intent = Intent(context , PhotoDetail::class.java)
                intent.putExtra("Position" , position)
                context?.startActivity(intent)
            }


        })
        Glide.with(holder.itemView)
            .load(photoModel.url)
            .placeholder(R.mipmap.ic_error)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        if (photoList==null)return 0
        return photoList.size
    }
    class PhotoViewHolder : RecyclerView.ViewHolder {
        var img : ImageView
        constructor(view: View) : super(view) {
            img = view.findViewById(R.id.imageView)
        }
    }

}