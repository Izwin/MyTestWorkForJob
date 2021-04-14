package com.example.myappforjob.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myappforjob.R
import com.example.myappforjob.model.PhotoModel
import com.example.myappforjob.webclient.WebHelper
import kotlinx.coroutines.runBlocking

class PhotoDetail : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        imageView = findViewById(R.id.detail_image)
        textView = findViewById(R.id.detail_text)
        button = findViewById(R.id.return_button)

        val pos:Int = intent.extras!!.getInt("Position")
        var photo:PhotoModel
        runBlocking { photo = WebHelper.getPhotoList()[pos]}

        button.setOnClickListener{
            finish()
        }

        textView.text = photo.url
        textView.setOnClickListener{
            val intent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse(textView.text.toString()))
            startActivity(intent)
        }

        Glide.with(this)
            .load(photo.url)
            .placeholder(R.mipmap.ic_error)
            .into(imageView)




    }
}