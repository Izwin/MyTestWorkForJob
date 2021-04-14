package com.example.myappforjob.webclient

import android.util.Log
import com.example.myappforjob.model.PhotoModel
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException
import java.net.URL

object WebHelper {
    //Использовал такой костыльный вариант так как не retrofit, не okhttp не смог запарсить json.
    private val client = OkHttpClient()
    lateinit var string: String
    suspend fun getPhotoList() : ArrayList<PhotoModel> {

        val photoArray = ArrayList<PhotoModel>()
        var str: String = ""
        try {
            GlobalScope.launch() {
                str = URL("http://dev-tasks.alef.im/task-m-001/list.php").readText()
                str = str.replace("[", "")
                str = str.replace("]", "")
                str = str.replace("\"", "")
                Log.d("KO", str)
                var strArr: Array<String> = str.split(",")!!.toTypedArray()
                for (one: String in strArr) {
                    Log.d("KO1", one)
                    photoArray.add(PhotoModel(one))
                }
            }.join()
        }
        finally {
            Log.d("TAG", "getPhotoList: 11")
            return photoArray;
        }






    }
    suspend fun getStrFromUrl(str:String):String{
        return URL("http://dev-tasks.alef.im/task-m-001/list.php").readText()
        delay(1000L)

    }



}