package com.khagani.posttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khagani.posttask.adapter.RecyclerViewAdapter
import com.khagani.posttask.model.PostsModel
import com.khagani.posttask.service.PostsAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener{

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var postsModels: ArrayList<PostsModel>? = null
    private  var recyclerViewAdapter : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager : RecyclerView.LayoutManager=LinearLayoutManager(this)
         recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostsAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<PostsModel>> {
            override fun onResponse(
                call: Call<List<PostsModel>>,
                response: Response<List<PostsModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        postsModels=ArrayList(it)


                        postsModels?.let {
                            recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter=recyclerViewAdapter
                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<PostsModel>>, t: Throwable) {
               t.printStackTrace()
            }

        })

    }

    override fun onItemClick(postsModel: PostsModel) {
        Toast.makeText(this, "Clicked: ${postsModel.userId}", Toast.LENGTH_LONG).show()
    }
}