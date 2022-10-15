package com.example.testtask.data.local_data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testtask.data.local_data_source.models.PizzaEntity

@Database(entities = [PizzaEntity::class], version = 1, exportSchema = false)
abstract class PizzaDatabase: RoomDatabase(){
    abstract fun dao(): PizzaDao

    companion object{
        @Volatile
        var database: PizzaDatabase? = null

        fun getInstance(context: Context): PizzaDatabase?{
            if (database == null){
                synchronized(this){
                    var db = Room.databaseBuilder(
                        context.applicationContext,
                        PizzaDatabase::class.java, "pizza_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    database = db
                    return db
                }
            }
            return database
        }
    }
}