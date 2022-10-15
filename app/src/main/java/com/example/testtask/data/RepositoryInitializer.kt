package com.example.testtask.data

import android.content.Context
import com.example.testtask.data.local_data_source.PizzaDao
import com.example.testtask.data.local_data_source.PizzaDatabase
import com.example.testtask.data.remote_data_source.PizzaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryInitializer {
    private var api: PizzaApi? = null
    private var dao: PizzaDao? = null
    private lateinit var repository: RepositoryImp

    fun getRepository(context: Context): RepositoryImp {
        if (api == null){
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://sosinvitalii.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            api = retrofit.create(PizzaApi::class.java)
        }
        if (dao == null){
            dao = PizzaDatabase.getInstance(context)?.dao()
        }
        if (api != null && dao != null)
            repository = RepositoryImp(api!!, dao!!)
        return repository
    }
}